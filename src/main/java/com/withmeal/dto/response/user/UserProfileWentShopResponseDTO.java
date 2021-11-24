package com.withmeal.dto.response.user;

import com.withmeal.domain.post.entity.Post;
import com.withmeal.domain.shop.Shop;
import lombok.Builder;
import lombok.Getter;

/**
 * created by Gyunny 2021/11/24
 */
@Builder
@Getter
public class UserProfileWentShopResponseDTO {

    private Long shopId;
    private String shopImage;
    private String shopName;

    public static UserProfileWentShopResponseDTO from(Post post) {
        Shop shop = post.getShop();
        return UserProfileWentShopResponseDTO.builder()
                .shopId(shop.getId())
                .shopImage(shop.getShopImage())
                .shopName(shop.getShopName())
                .build();
    }
}
