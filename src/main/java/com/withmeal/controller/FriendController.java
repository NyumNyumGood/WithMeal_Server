package com.withmeal.controller;

import com.withmeal.dto.response.ApiResponse;
import com.withmeal.dto.response.follow.FollowProfileListResponseDTO;
import com.withmeal.service.FriendService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by Gyunny 2021/11/09
 */
@RequestMapping("/api/v1/friend")
@RequiredArgsConstructor
@RestController
public class FriendController {

    private final FriendService friendService;

    @ApiOperation("피드 친구 프로필 조회")
    //@Auth
    @GetMapping("/profiles")
    public ApiResponse<List<FollowProfileListResponseDTO>> getFriendsProfile() {
        //var userId = AuthContext.getCurrentUserId();
        return ApiResponse.success(HttpStatus.OK, friendService.getFriendsProfile(1L));
    }

}
