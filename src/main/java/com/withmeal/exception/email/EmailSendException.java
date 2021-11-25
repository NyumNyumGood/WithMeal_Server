package com.withmeal.exception.email;

import com.withmeal.exception.BusinessException;
import com.withmeal.exception.ErrorCode;

/**
 * created by Gyunny 2021/11/26
 */
public class EmailSendException extends BusinessException {

    public EmailSendException() {
        super(ErrorCode.EMAIL_SEND_EXCEPTION);
    }

}
