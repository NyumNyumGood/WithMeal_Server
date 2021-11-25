package com.withmeal.dto.email;

import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * created by Gyunny 2021/11/26
 */
@Getter
public class EmailCodeDTO {

    @NotNull
    private String code;

}
