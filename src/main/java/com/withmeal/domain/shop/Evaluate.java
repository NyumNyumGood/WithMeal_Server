package com.withmeal.domain.shop;

import lombok.Getter;

/**
 * created by Gyunny 2021/11/09
 */
@Getter
public enum Evaluate {

    COST_PERFORMANCE("가성비"),
    CLEAN("청결"),
    MOOD("분위기"),
    KIND("친절"),
    TASTE("맛"),
    ;

    private final String korean;

    Evaluate(String korean) {
        this.korean = korean;
    }

}
