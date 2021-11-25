package com.withmeal.dto.response.user;

import com.withmeal.domain.post.entity.Post;
import com.withmeal.domain.post.entity.PostBookmark;
import com.withmeal.domain.post.entity.PostImages;
import com.withmeal.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Gyunny 2021/11/25
 */
@Builder
@Getter
public class UserProfileShopWantResponseDTO {

    private Long postId;
    private List<String> postImages;
    private String category;
    private String content;
    private List<String> userImages;

    public static UserProfileShopWantResponseDTO from(Post post, List<User> users) {
        return UserProfileShopWantResponseDTO.builder()
                .postId(post.getId())
                .postImages(post.getPostImages().stream().map(PostImages::getImageUrl).collect(Collectors.toList()))
                .category(post.getCategory().getKorean())
                .content(post.getContent())
                .userImages(users.stream().map(User::getProfileImage).collect(Collectors.toList()))
                .build();
    }

    @AllArgsConstructor
    @Getter
    private static class UserVO {
        private Long userId;
        private String userImage;
    }

}
