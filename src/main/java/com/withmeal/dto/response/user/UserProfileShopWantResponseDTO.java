package com.withmeal.dto.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.withmeal.domain.post.entity.PostImages;
import com.withmeal.domain.shop.entity.Shop;
import com.withmeal.domain.user.entity.User;
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

    private Long shopId;
    private List<String> postImages;
    private String category;
    private String content;

    @JsonProperty("users")
    private List<UserDTO> userDTOs;

    public static UserProfileShopWantResponseDTO from(Shop shop, List<User> users) {
        return UserProfileShopWantResponseDTO.builder()
                .shopId(shop.getId())
                .postImages(shop.getPostImages().stream().map(PostImages::getImageUrl).collect(Collectors.toList()))
                .category(shop.getCategory())
                .content(shop.getCategory())
                .userDTOs(users.stream().map(user -> new UserDTO(user.getId(), user.getProfileImage())).collect(Collectors.toList()))
                .build();
    }

    @AllArgsConstructor
    @Getter
    private static class UserDTO {
        private Long userId;
        private String userImage;
    }

}
