package com.withmeal.controller;

import com.withmeal.dto.response.ApiResponse;
import com.withmeal.dto.response.token.TokenResponseDTO;
import com.withmeal.infra.aop.AuthContext;
import com.withmeal.infra.aop.ReAuth;
import com.withmeal.infra.jwt.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Gyunny 2021/11/09
 */
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class TokenController {

    private final JwtService jwtService;

    @ApiOperation("JWT 토큰 발급 임시 API")
    @GetMapping("/token")
    public ApiResponse<TokenResponseDTO> getAccessToken() {
        return ApiResponse.success(HttpStatus.OK, jwtService.createTokenResponse(1L));
    }

    @ApiOperation("JWT 재발급")
    @ReAuth
    @PostMapping("/token/refresh")
    public ApiResponse<TokenResponseDTO> reIssueToken() {
        Long userId = AuthContext.getCurrentUserId();
        return ApiResponse.success(HttpStatus.OK, jwtService.createTokenResponse(userId));
    }

}
