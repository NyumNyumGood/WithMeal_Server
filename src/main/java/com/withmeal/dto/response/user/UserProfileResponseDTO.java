package com.withmeal.dto.response.user;

import com.withmeal.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

/**
 * created by Gyunny 2021/11/24
 */
@Builder
@Getter
public class UserProfileResponseDTO {

    private Long userId;
    private String nickname;
    private String imageUrl;
    private String universityName;
    private Long followerCount;
    private Long followingCount;
    private int reviewCount;

    public static UserProfileResponseDTO from(User user, Long followerCount, Long followingCount) {
        return UserProfileResponseDTO.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .imageUrl(user.getProfileImage())
                .universityName(user.getUniversityName())
                .followerCount(followerCount)
                .followingCount(followingCount)
                .build();
    }

}
