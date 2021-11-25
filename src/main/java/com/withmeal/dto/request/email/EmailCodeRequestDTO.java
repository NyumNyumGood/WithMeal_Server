package com.withmeal.dto.request.email;

import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * created by Gyunny 2021/11/26
 */
@Getter
public class EmailCodeRequestDTO {

    @NotNull
    private String code;

}
