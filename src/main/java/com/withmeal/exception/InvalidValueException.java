package com.withmeal.exception;

/**
 * created by Gyunny 2021/11/09
 */
public class InvalidValueException extends BusinessException {

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }

}
