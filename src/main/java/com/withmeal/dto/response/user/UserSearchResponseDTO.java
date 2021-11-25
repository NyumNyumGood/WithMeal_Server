package com.withmeal.dto.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.withmeal.domain.post.entity.Post;
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
public class UserSearchResponseDTO {

    private Long userId;
    private String nickname;
    private String withFollowingNickname;

    @JsonProperty("shops")
    private List<ShopDTO> shopDTOs;

    public static UserSearchResponseDTO from(User user) {
        List<Shop> shops = user.getPosts().stream().map(Post::getShop).collect(Collectors.toList());
        return UserSearchResponseDTO.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .withFollowingNickname("Gyunny1")
                .shopDTOs(shops.stream()
                        .map(shop -> new ShopDTO(shop.getId(), shop.getShopName(), shop.getPostImages().get(0).getImageUrl(), shop.getCategory()))
                        .collect(Collectors.toList()))
                .build();
    }

    @Getter @AllArgsConstructor
    public static class ShopDTO {
        private Long shopId;
        private String shopName;
        private String shopImage;
        private String category;
    }

}
