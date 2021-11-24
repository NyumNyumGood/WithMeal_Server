package com.withmeal.domain.post.entity;

import lombok.Getter;

/**
 * created by Gyunny 2021/11/24
 */
@Getter
public enum Category {

    KOREAN_FOOD("한식"),
    SNACK_BAR("분식"),
    CAFE_DESSERT("카페, 디저트"),
    CHINESE_FOOD("중식"),
    JAPAN_FOOD("일식"),
    FAST_FOOD("패스트 푸드"),
    WESTERN_FOOD("양식"),
    ASIAN_FOOD("아시안")
    ;

    private final String korean;

    Category(String korean) {
        this.korean = korean;
    }

}
