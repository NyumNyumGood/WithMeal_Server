package com.withmeal.aop;

import com.withmeal.domain.user.entity.User;
import com.withmeal.domain.user.repository.UserRepository;
import com.withmeal.exception.jwt.DiscardRefreshTokenException;
import com.withmeal.exception.jwt.JwtException;
import com.withmeal.exception.user.UserNotFoundException;
import com.withmeal.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * created by Gyunny 2021/11/09
 */
@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class AuthAspect {

    private static final String ACCESS_TOKEN_NAME = "accessToken";
    private static final String REFRESH_TOKEN_NAME = "refreshToken";

    private final HttpServletRequest httpServletRequest;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Around("@annotation(Auth)")
    public Object accessToken(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            var accessToken = httpServletRequest.getHeader(ACCESS_TOKEN_NAME);
            var payload = jwtService.getAccessTokenPayload(accessToken).getId();
            AuthContext.USER_CONTEXT.set(findUser(payload).getId());

            return pjp.proceed(pjp.getArgs());
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException |
                UnsupportedJwtException | IllegalArgumentException e) {
            throw new JwtException();
        }
    }

    @Around("@annotation(ReAuth)")
    public Object refreshToken(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            var refreshToken = httpServletRequest.getHeader(REFRESH_TOKEN_NAME);
            var payload = jwtService.getRefreshTokenPayload(refreshToken).getId();
            AuthContext.USER_CONTEXT.set(findUser(payload).getId());
            checkLatestRefreshToken(refreshToken, payload);

            return pjp.proceed(pjp.getArgs());
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException |
                UnsupportedJwtException | IllegalArgumentException e) {
            throw new JwtException();
        }
    }


    private void checkLatestRefreshToken(final String requestRefreshToken, final Long userId) {
        if (!findUser(userId).getRefreshToken().equals(requestRefreshToken)) {
            throw new DiscardRefreshTokenException();
        }
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

}
