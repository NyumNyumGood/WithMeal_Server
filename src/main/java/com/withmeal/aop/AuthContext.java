package com.withmeal.aop;

import io.jsonwebtoken.JwtException;

/**
 * created by Gyunny 2021/11/09
 */
public class AuthContext {

    public static final ThreadLocal<Long> USER_CONTEXT = new ThreadLocal<>();

    public static Long getCurrentUserId() {
        if (AuthContext.USER_CONTEXT.get() != null) {
            return AuthContext.USER_CONTEXT.get();
        }

        throw new JwtException("JWT Error !");
    }

}
