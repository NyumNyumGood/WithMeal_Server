package com.withmeal.exception.aws;

import com.withmeal.exception.BusinessException;
import com.withmeal.exception.ErrorCode;

/**
 * created by Gyunny 2021/11/26
 */
public class FileIOException extends BusinessException {

    public FileIOException() {
        super(ErrorCode.FILE_IO_Exception);
    }

}
