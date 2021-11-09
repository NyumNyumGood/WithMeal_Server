package com.withmeal.controller;

import com.withmeal.aop.Auth;
import com.withmeal.aop.AuthContext;
import com.withmeal.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Gyunny 2021/11/09
 */
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @ApiOperation("피드에서 프로필 ")
    @Auth
    @GetMapping("/post")
    public String getPostFeed() {
        var userId = AuthContext.getCurrentUserId();
        postService.getPostFeed(userId);
        return "test";
    }

}
