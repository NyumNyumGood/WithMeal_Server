package com.withmeal.controller;

import com.withmeal.dto.response.ApiResponse;
import com.withmeal.dto.response.token.TokenResponseDTO;
import com.withmeal.service.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * created by Gyunny 2021/11/09
 */
@RequiredArgsConstructor
@RestController
public class TestController {

    private final JwtService jwtService;

    @GetMapping
    public String ping() {
        return "pong!!";
    }

    @ApiOperation("JWT 토큰 발급 임시 API")
    @GetMapping("/api/v1/token")
    public ApiResponse<TokenResponseDTO> getAccessToken() {
        return ApiResponse.success(HttpStatus.OK, jwtService.createTokenResponse(1L));
    }

}
