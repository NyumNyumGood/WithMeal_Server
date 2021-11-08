package com.withmeal.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * created by Gyunny 2021/11/09
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "jwt")
@Configuration
public class JwtProperties {

    private String accessTokenSecretKey;
    private Long accessTokenValidTime;

}
