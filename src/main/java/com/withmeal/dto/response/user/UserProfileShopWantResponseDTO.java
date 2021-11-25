package com.withmeal.dto.response.user;

import com.withmeal.domain.shop.Shop;
import com.withmeal.domain.shop.ShopImage;
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

    private Long shopId;
    private List<String> postImages;
    private String category;
    private String content;
    private List<String> userImages;

    public static UserProfileShopWantResponseDTO from(Shop shop, List<User> users) {
        return UserProfileShopWantResponseDTO.builder()
                .shopId(shop.getId())
                .postImages(shop.getShopImage().stream().map(ShopImage::getShopImage).collect(Collectors.toList()))
                .category(shop.getCategory())
                .content(shop.getCategory())
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
