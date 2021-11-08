package com.withmeal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Gyunny 2021/11/09
 */
@RestController
public class TestController {

    @GetMapping
    public String ping() {
        return "pong";
    }

}
