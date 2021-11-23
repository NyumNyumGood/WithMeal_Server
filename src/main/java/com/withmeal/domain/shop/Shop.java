package com.withmeal.domain.shop;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * created by Gyunny 2021/11/09
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Shop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shopName;

    private String shopTelephone;

}
