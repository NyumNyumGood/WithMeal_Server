package com.withmeal.exception.user;

import com.withmeal.exception.EntityNotFoundException;
import com.withmeal.exception.ErrorCode;

/**
 * created by Gyunny 2021/11/09
 */
public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

}
