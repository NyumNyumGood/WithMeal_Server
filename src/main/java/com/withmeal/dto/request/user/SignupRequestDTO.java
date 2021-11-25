package com.withmeal.dto.request.user;

import com.withmeal.domain.user.entity.User;
import lombok.Getter;

/**
 * created by Gyunny 2021/11/26
 */
@Getter
public class SignupRequestDTO {

    private String loginId;
    private String password;
    private String nickname;

    public User toEntity(String encodePassword) {
        return User.builder()
                .loginId(loginId)
                .password(encodePassword)
                .nickname(nickname)
                .build();
    }

}
