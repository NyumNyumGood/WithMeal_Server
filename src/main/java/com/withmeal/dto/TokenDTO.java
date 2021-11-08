package com.withmeal.dto;

import lombok.Getter;

import java.util.Date;

/**
 * created by Gyunny 2021/11/09
 */
@Getter
public class TokenDTO {

    private final Long id;
    private final Date expiredTime;

    public TokenDTO(Long id, Date expiredTime) {
        this.id = id;
        this.expiredTime = expiredTime;
    }

}
