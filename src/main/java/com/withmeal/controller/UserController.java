package com.withmeal.controller;

import com.withmeal.dto.response.ApiResponse;
import com.withmeal.dto.response.user.UserProfileResponseDTO;
import com.withmeal.dto.response.user.UserProfileWentShopResponseDTO;
import com.withmeal.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by Gyunny 2021/11/24
 */
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation("유저 프로필 상단 조회")
    //@Auth
    @GetMapping("/profile")
    public ApiResponse<UserProfileResponseDTO> getUserInfo() {
        return ApiResponse.success(HttpStatus.OK, userService.getUserProfile(1L));
    }

    @ApiOperation("유저 프로필 가봤어요 조회")
    //@Auth
    @GetMapping("/profile/post")
    public ApiResponse<List<UserProfileWentShopResponseDTO>> getUserPostInfo() {
        return ApiResponse.success(HttpStatus.OK, userService.getUserProfileWentShop(1L));
    }

}