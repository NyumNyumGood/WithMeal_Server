package com.withmeal.controller;

import com.withmeal.dto.response.ApiResponse;
import com.withmeal.dto.response.post.PostFeedResponseDTO;
import com.withmeal.infra.aop.Auth;
import com.withmeal.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by Gyunny 2021/11/25
 */
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @ApiOperation("피드에서 상단 프로필 조회")
    //@Auth
    @GetMapping
    public ApiResponse<List<PostFeedResponseDTO>> getPostFeed() {
        //var userId = AuthContext.getCurrentUserId();
        return ApiResponse.success(HttpStatus.OK, postService.getHomeFeed(1L));
    }

    @ApiOperation("게시글 리뷰 작성")
    //@Auth
    @PostMapping
    public ApiResponse<Object> writePostReview() {
        postService.writePostReview();
        return null;
    }

}
