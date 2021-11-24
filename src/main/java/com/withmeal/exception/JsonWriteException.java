package com.withmeal.exception;

/**
 * created by Gyunny 2021/11/09
 */
public class JsonWriteException extends InvalidValueException {

    public JsonWriteException() {
        super(ErrorCode.JSON_WRITE_ERROR);
    }

}