package com.withmeal.exception.jwt;

import com.withmeal.exception.ErrorCode;
import com.withmeal.exception.InvalidValueException;

/**
 * created by Gyunny 2021/11/26
 */
public class DiscardRefreshTokenException extends InvalidValueException {

    public DiscardRefreshTokenException() {
        super(ErrorCode.DISCARD_REFRESH_TOKEN);
    }

}
