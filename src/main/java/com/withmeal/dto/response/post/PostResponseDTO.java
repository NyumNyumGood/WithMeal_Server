package com.withmeal.dto.response.post;

import com.withmeal.domain.post.entity.Post;
import com.withmeal.domain.post.entity.PostEvaluate;
import com.withmeal.domain.post.entity.PostImages;
import com.withmeal.domain.post.repository.PostRepository;
import com.withmeal.domain.shop.Evaluate;
import com.withmeal.domain.user.User;
import com.withmeal.domain.user.UserWith;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Gyunny 2021/11/24
 */
@Builder
@Getter
public class PostResponseDTO {

    private Long postId;
    private Long userId;
    private String nickname;
    private String profileImage;
    private String title;
    private String content;
    private String foodCategory;
    private List<String> withNicknames;
    private List<String> images;
    private List<Evaluate> evaluates;
    private LocalDate createdAt;

    public static PostResponseDTO from(Post post) {
        User user = post.getUser();
        return PostResponseDTO.builder()
                .postId(post.getId())
                .userId(user.getId())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .title(post.getTitle())
                .content(post.getContent())
                .foodCategory(post.getCategory().getKorean())
                .withNicknames(post.getUserWiths().stream().map(UserWith::getNickname).collect(Collectors.toList()))
                .images(post.getPostImages().stream().map(PostImages::getImageUrl).collect(Collectors.toList()))
                .evaluates(post.getPostEvaluates().stream().map(PostEvaluate::getEvaluate).collect(Collectors.toList()))
                .createdAt(post.getCreatedTime().toLocalDate())
                .build();
    }

}
