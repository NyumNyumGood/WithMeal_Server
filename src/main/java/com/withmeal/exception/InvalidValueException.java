package com.withmeal.exception;

/**
 * created by Gyunny 2021/11/09
 */
public class InvalidValueException extends BusinessException {

    public InvalidValueException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InvalidValueException(String message) {
        super(message, ErrorCode.INVALID_TYPE_VALUE);
    }

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }

}
