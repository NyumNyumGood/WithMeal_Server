package com.withmeal.dto.response.shop;

import com.withmeal.domain.shop.entity.ShopBookmark;
import lombok.Builder;
import lombok.Getter;

/**
 * created by Gyunny 2021/11/27
 */
@Builder
@Getter
public class ShopMapResponseDTO {

    private Long shopId;
    private String latitude;
    private String longitude;

    public static ShopMapResponseDTO from(ShopBookmark shopBookmark) {
        return ShopMapResponseDTO.builder()
                .shopId(shopBookmark.getShop().getId())
                .latitude(shopBookmark.getShop().getLatitude())
                .longitude(shopBookmark.getShop().getLongitude())
                .build();
    }

}
