package com.withmeal.dto.request.email;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * created by Gyunny 2021/11/26
 */
@Getter
public class EmailRequestDTO {

    @NotBlank
    @Email
    private String email;

}
