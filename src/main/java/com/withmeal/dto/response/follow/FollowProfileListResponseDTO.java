package com.withmeal.dto.response.follow;

import com.withmeal.domain.follow.Follow;
import com.withmeal.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

/**
 * created by Gyunny 2021/11/09
 */
@Builder
@Getter
public class FollowProfileListResponseDTO {

    private String nickname;
    private String profileImageUrl;

    public static FollowProfileListResponseDTO from(Follow follow) {
        User user = follow.getFollowing();
        return FollowProfileListResponseDTO.builder()
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImage())
                .build();
    }

}
