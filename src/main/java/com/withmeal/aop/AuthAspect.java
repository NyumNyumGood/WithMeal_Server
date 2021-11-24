package com.withmeal.aop;

import com.withmeal.domain.user.User;
import com.withmeal.domain.user.UserRepository;
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

    private final HttpServletRequest httpServletRequest;
    private final UserRepository memberRepository;
    private final JwtService jwtService;

    @Around("@annotation(Auth)")
    public Object accessToken(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            var accessToken = httpServletRequest.getHeader(ACCESS_TOKEN_NAME);
            var payload = jwtService.getAccessTokenPayload(accessToken).getId();
            setAuthContextUserId(payload);

            return pjp.proceed(pjp.getArgs());
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException |
                UnsupportedJwtException | IllegalArgumentException e) {
            throw new JwtException();
        }
    }

    private void setAuthContextUserId(final Long userId) {
        User user = memberRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        AuthContext.USER_CONTEXT.set(user.getId());
    }

}
