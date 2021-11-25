package com.withmeal.dto.request.user;

import com.withmeal.domain.user.entity.User;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * created by Gyunny 2021/11/26
 */
@Getter
public class SignupRequestDTO {

    @NotNull
    private String loginId;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

    public User toEntity(String encodePassword) {
        return User.builder()
                .loginId(loginId)
                .password(encodePassword)
                .nickname(nickname)
                .build();
    }

}
