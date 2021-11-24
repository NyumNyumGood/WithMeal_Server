package com.withmeal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.withmeal.dto.TokenDTO;
import com.withmeal.dto.response.token.TokenResponseDTO;
import com.withmeal.exception.jwt.JsonWriteException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    @Value("${jwt.access_token_valid_time}")
    private Long accessTokenValidTime;

    private final ObjectMapper objectMapper;

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

    public TokenResponseDTO createTokenResponse(final Long userId) {
        var accessToken = createAccessToken(userId);

        return TokenResponseDTO.builder()
                .accessToken(accessToken)
                .accessTokenExpiredAt(getAccessTokenPayload(accessToken).getExpiredTime())
                .build();
    }

    public TokenDTO getAccessTokenPayload(final String accessToken) {
        return getPayload(accessToken, accessTokenSecretKey);
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
