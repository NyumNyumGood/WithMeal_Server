package com.withmeal.exception.user;

import com.withmeal.exception.ErrorCode;
import com.withmeal.exception.InvalidValueException;

/**
 * created by Gyunny 2021/11/26
 */
public class PasswordNotMatchException extends InvalidValueException {

    public PasswordNotMatchException() {
        super(ErrorCode.PASSWORD_NOT_MATCH);
    }

}
