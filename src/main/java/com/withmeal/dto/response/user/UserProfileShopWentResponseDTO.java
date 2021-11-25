package com.withmeal.dto.response.user;

import com.withmeal.domain.post.entity.Post;
import com.withmeal.domain.post.entity.PostImages;
import com.withmeal.domain.shop.entity.Shop;
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
public class UserProfileShopWentResponseDTO {

    private Long shopId;
    private List<String> shopImage;
    private String shopName;
    private LocalDate createdAt;

    public static UserProfileShopWentResponseDTO from(Post post) {
        Shop shop = post.getShop();
        return UserProfileShopWentResponseDTO.builder()
                .shopId(shop.getId())
                .shopImage(shop.getPostImages().stream().map(PostImages::getImageUrl).collect(Collectors.toList()))
                .shopName(shop.getShopName())
                .createdAt(post.getCreatedTime().toLocalDate())
                .build();
    }
}
