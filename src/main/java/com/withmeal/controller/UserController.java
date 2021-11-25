package com.withmeal.controller;

import com.withmeal.dto.response.ApiResponse;
import com.withmeal.dto.response.user.UserProfileResponseDTO;
import com.withmeal.dto.response.user.UserProfileShopWantResponseDTO;
import com.withmeal.dto.response.user.UserProfileShopWentResponseDTO;
import com.withmeal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private static final String SHOP_WENT = "went";
    private final UserService userService;

    @ApiOperation("유저 프로필 상단 조회")
    //@Auth
    @GetMapping("/profile")
    public ApiResponse<UserProfileResponseDTO> getUserInfo() {
        return ApiResponse.success(HttpStatus.OK, userService.getUserProfile(1L));
    }

    @ApiOperation("유저 프로필 가고 싶어요")
    //@Auth
    @GetMapping("/profile/want")
    public ApiResponse<List<UserProfileShopWantResponseDTO>> getUserWant() {
        return ApiResponse.success(HttpStatus.OK, userService.getUserProfileWantShop(1L));
    }

    @ApiOperation("유저 프로필 가봤어요")
    //@Auth
    @GetMapping("/profile/went")
    public ApiResponse<List<UserProfileShopWentResponseDTO>> getUserProfileWent() {
        return ApiResponse.success(HttpStatus.OK, userService.getUserProfileWentShop(1L));
    }

}
