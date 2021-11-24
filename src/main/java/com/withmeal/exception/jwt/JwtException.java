package com.withmeal.exception.jwt;

import com.withmeal.exception.ErrorCode;
import com.withmeal.exception.InvalidValueException;

/**
 * created by Gyunny 2021/11/24
 */
public class JwtException extends InvalidValueException {

    public JwtException() {
        super(ErrorCode.INVALID_JWT);
    }

}
