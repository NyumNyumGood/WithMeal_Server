package com.withmeal.dto.request.user;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * created by Gyunny 2021/11/26
 */
@Builder
@Getter
public class SignInRequestDTO {

    @NotNull
    private String loginId;

    @NotNull
    private String password;

}
