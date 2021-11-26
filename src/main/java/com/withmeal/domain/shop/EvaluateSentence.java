package com.withmeal.domain.shop;

import lombok.Getter;

/**
 * created by Gyunny 2021/11/26
 */
@Getter
public enum EvaluateSentence {

    DELICIOUS("말잇못.. 말을 잇지 못할 맛이에요."),
    KIND("직원 분들의 친절함에 감동이 두 배!"),
    MONEY("갓성비가 내려와~ 적절한 금액대에요"),
    CLEAN("반짝반짝, 식당이 깔끔하고 청결해요"),
    MOOD("여기가 사진 맛집? 분위기가 좋아요")
    ;

    private final String korean;

    EvaluateSentence(String korean) {
        this.korean = korean;
    }

}
