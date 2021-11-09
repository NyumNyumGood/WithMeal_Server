package com.withmeal.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * created by Gyunny 2021/11/09
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Global
    INVALID_INPUT_VALUE(400, "Global001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "Global002", "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "Global004", "Server Error"),
    INVALID_TYPE_VALUE(400, "Global005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "Global006", "Access is Denied"),

    // USer
    USER_NOT_FOUND(400, "User001", "User Not Found Exception"),
    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

}