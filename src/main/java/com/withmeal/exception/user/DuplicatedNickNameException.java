package com.withmeal.exception.user;

import com.withmeal.exception.ErrorCode;
import com.withmeal.exception.InvalidValueException;

/**
 * created by Gyunny 2021/11/26
 */
public class DuplicatedNickNameException extends InvalidValueException {

    public DuplicatedNickNameException() {
        super(ErrorCode.NICKNAME_DUPLICATE);
    }

}
