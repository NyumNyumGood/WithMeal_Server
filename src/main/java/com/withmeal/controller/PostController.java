package com.withmeal.controller;

import com.withmeal.dto.response.ApiResponse;
import com.withmeal.dto.response.post.PostResponseDTO;
import com.withmeal.service.PostService;
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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @ApiOperation("피드에서 프로필 ")
    //@Auth
    @GetMapping("/post")
    public ApiResponse<List<PostResponseDTO>> getPostFeed() {
        //var userId = AuthContext.getCurrentUserId();
        return ApiResponse.success(HttpStatus.OK, postService.getHomeFeed(1L));
    }

}
