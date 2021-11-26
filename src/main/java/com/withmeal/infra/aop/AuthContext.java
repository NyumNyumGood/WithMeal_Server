package com.withmeal.infra.aop;

import com.withmeal.exception.jwt.JwtException;

/**
 * created by Gyunny 2021/11/09
 */
public class AuthContext {

    public static final ThreadLocal<Long> USER_CONTEXT = new ThreadLocal<>();

    public static Long getCurrentUserId() {
        if (AuthContext.USER_CONTEXT.get() != null) {
            return AuthContext.USER_CONTEXT.get();
        }

        throw new JwtException();
    }

}
