package com.withmeal.controller;

import com.withmeal.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/token")
    public String getAccessToken() {
        return jwtService.createAccessToken(1L);
    }

}
