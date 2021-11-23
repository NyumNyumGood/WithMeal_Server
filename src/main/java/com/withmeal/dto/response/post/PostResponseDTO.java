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

import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Gyunny 2021/11/24
 */
@Builder
@Getter
public class PostResponseDTO {

    private Long postId;
    private String nickname;
    private String title;
    private String content;
    private String category;
    private List<String> withNicknames;
    private List<String> images;
    private List<Evaluate> evaluates;

    public static PostResponseDTO from(Post post) {
        User user = post.getUser();
        return PostResponseDTO.builder()
                .postId(post.getId())
                .nickname(user.getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .withNicknames(post.getUserWiths().stream().map(UserWith::getNickname).collect(Collectors.toList()))
                .images(post.getPostImages().stream().map(PostImages::getImageUrl).collect(Collectors.toList()))
                .evaluates(post.getPostEvaluates().stream().map(PostEvaluate::getEvaluate).collect(Collectors.toList()))
                .build();
    }

}
