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
    JSON_WRITE_ERROR(401, "Global007", "JSON content that are not pure I/O problems"),

    // JWT
    INVALID_JWT(401, "J001", "JWT가 없거나 잘못된 값 입니다"),
    DISCARD_REFRESH_TOKEN(401, "J001", "폐기된 RefreshToken 입니다."),

    // Email
    EMAIL_SEND_EXCEPTION(500, "E001", "이메일 보내기 실패"),

    // User
    USER_NOT_FOUND(400, "User001", "User Not Found Exception"),
    NICKNAME_DUPLICATE(400, "User002", "User Nickname Duplicate!"),
    PASSWORD_NOT_MATCH(400, "User003", "Password Not Match Exception"),
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