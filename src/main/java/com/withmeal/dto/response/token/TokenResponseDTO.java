package com.withmeal.dto.response.token;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * created by Gyunny 2021/11/09
 */
@Getter
public class TokenResponseDTO {

    private final String accessToken;
    private final Date accessTokenExpiredAt;

    @Builder
    public TokenResponseDTO(String accessToken, Date accessTokenExpiredAt) {
        this.accessToken = accessToken;
        this.accessTokenExpiredAt = accessTokenExpiredAt;
    }

}
