package com.withmeal.exception;

import com.withmeal.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * created by Gyunny 2021/11/09
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생'
     * BindingResult: 에러가 발생한 Binding result 반환
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final var response = ErrorResponse.of(ErrorCode.INVALID_TYPE_VALUE, e.getBindingResult());
        return ApiResponse.failure(HttpStatus.BAD_REQUEST, response.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ApiResponse<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException", e);
        final var response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
        return ApiResponse.failure(HttpStatus.BAD_REQUEST, response.getMessage());
    }

    @ExceptionHandler(BindException.class)
    protected ApiResponse<?> handleBindException(BindException e) {
        log.error("handleBindException", e);
        final var response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return ApiResponse.failure(HttpStatus.BAD_REQUEST, response.getMessage());
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ApiResponse<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        final var response = ErrorResponse.of(e);
        return ApiResponse.failure(HttpStatus.BAD_REQUEST, response.getMessage());
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ApiResponse<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final var response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        return ApiResponse.failure(HttpStatus.BAD_REQUEST, response.getMessage());
    }

    /**
     * Business custom Exception Handler
     */
    @ExceptionHandler(BusinessException.class)
    protected ApiResponse<?> handleBusinessException(final BusinessException e) {
        log.error("BusinessException ", e);
        final var errorCode = e.getErrorCode();
        final var response = ErrorResponse.of(errorCode);
        return ApiResponse.failure(HttpStatus.BAD_REQUEST, response.getMessage());
    }

    /**
     * 예상치 못한 에러를 잡기 위해 Exception handler 정의
     */
    @ExceptionHandler(Exception.class)
    protected ApiResponse<?> handleException(Exception e) {
        log.error("Exception (Unexpected) ", e);
        final var response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return ApiResponse.failure(HttpStatus.BAD_REQUEST, response.getMessage());
    }

}