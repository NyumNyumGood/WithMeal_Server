package com.withmeal.exception;

/**
 * created by Gyunny 2021/11/09
 */
public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
