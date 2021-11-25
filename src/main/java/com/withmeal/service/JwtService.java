package com.withmeal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.withmeal.domain.user.entity.User;
import com.withmeal.domain.user.repository.UserRepository;
import com.withmeal.dto.TokenDTO;
import com.withmeal.dto.response.token.TokenResponseDTO;
import com.withmeal.exception.jwt.JsonWriteException;
import com.withmeal.exception.user.UserNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * created by Gyunny 2021/11/09
 */
@RequiredArgsConstructor
@Service
public class JwtService {

    @Value("${jwt.access_token_secretKey}")
    private String accessTokenSecretKey;

    @Value("${jwt.refresh_token_secretKey}")
    private String refreshTokenSecretKey;

    @Value("${jwt.access_token_valid_time}")
    private Long accessTokenValidTime;

    @Value("${jwt.refresh_token_valid_time}")
    private Long refreshTokenValidTime;

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    private String createToken(final long payload, final String secretKey, final Long tokenValidTime) {
        var signatureAlgorithm = SignatureAlgorithm.HS256;
        var secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
        var signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(writeJsonAsString(payload))
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .compact();
    }

    private String writeJsonAsString(final Long payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new JsonWriteException();
        }
    }

    public String createAccessToken(final long payload) {
        return createToken(payload, accessTokenSecretKey, accessTokenValidTime);
    }

    @Transactional
    public String createRefreshToken(final long payload) {
        String refreshToken = createToken(payload, refreshTokenSecretKey, refreshTokenValidTime);
        User user = userRepository.findById(payload).orElseThrow(UserNotFoundException::new);
        user.changeRefreshToken(refreshToken);
        userRepository.save(user);
        return refreshToken;
    }

    public TokenResponseDTO createTokenResponse(final Long userId) {
        var accessToken = createAccessToken(userId);
        var refreshToken = createRefreshToken(userId);

        return TokenResponseDTO.builder()
                .accessToken(accessToken)
                .accessTokenExpiredAt(getAccessTokenPayload(accessToken).getExpiredTime())
                .refreshToken(refreshToken)
                .refreshTokenExpiredAt(getRefreshTokenPayload(refreshToken).getExpiredTime())
                .build();
    }

    public TokenDTO getAccessTokenPayload(final String accessToken) {
        return getPayload(accessToken, accessTokenSecretKey);
    }

    public TokenDTO getRefreshTokenPayload(final String refreshToken) {
        return getPayload(refreshToken, refreshTokenSecretKey);
    }

    private TokenDTO getPayload(final String token, final String secretKey) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();

        try {
            return new TokenDTO(objectMapper.readValue(claims.getSubject(), Long.class), claims.getExpiration());
        } catch (JsonProcessingException e) {
            throw new JsonWriteException();
        }
    }

}
