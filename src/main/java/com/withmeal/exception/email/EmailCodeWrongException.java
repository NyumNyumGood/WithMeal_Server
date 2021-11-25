package com.withmeal.exception.email;

import com.withmeal.exception.ErrorCode;
import com.withmeal.exception.InvalidValueException;

/**
 * created by Gyunny 2021/11/26
 */
public class EmailCodeWrongException extends InvalidValueException {

    public EmailCodeWrongException() {
        super(ErrorCode.EMAIL_INVALID_CODE);
    }

}
