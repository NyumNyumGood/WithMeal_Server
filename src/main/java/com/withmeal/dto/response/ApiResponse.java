package com.withmeal.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * created by Gyunny 2021/11/09
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ApiResponse<T> {

    private int status;
    private T data;

    public static <T> ApiResponse<T> success(HttpStatus httpStatus) {
        return new ApiResponse<>(httpStatus.value(), null);
    }

    public static <T> ApiResponse<T> success(HttpStatus httpStatus, T data) {
        return new ApiResponse<>(httpStatus.value(), data);
    }

    public static <T> ApiResponse<T> failure(HttpStatus httpStatus) {
        return new ApiResponse<>(httpStatus.value(), null);
    }

}
