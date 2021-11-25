package com.withmeal.controller;

import com.withmeal.dto.email.EmailCodeDTO;
import com.withmeal.dto.email.EmailDTO;
import com.withmeal.dto.response.ApiResponse;
import com.withmeal.service.EmailService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * created by Gyunny 2021/11/26
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService emailService;

    @ApiOperation("이메일 인증 코드 전송")
    @PostMapping("/send")
    public ApiResponse<Object> sendCodeToEmail(@RequestBody @Valid EmailDTO email) {
        emailService.sendEmailMessage(email.getEmail());
        return ApiResponse.success(HttpStatus.OK);
    }

    @ApiOperation("인증 코드 검증")
    @PostMapping("/verify")
    public ApiResponse<Object> verifyCode(@RequestBody @Valid EmailCodeDTO emailCodeDto) {
        emailService.verifyCode(emailCodeDto.getCode());
        return ApiResponse.success(HttpStatus.OK);
    }
}
