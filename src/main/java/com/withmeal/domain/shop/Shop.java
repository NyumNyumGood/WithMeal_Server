package com.withmeal.domain.shop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * created by Gyunny 2021/11/09
 */
@Entity
public class Shop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shopName;

    private String shopTelephone;

}
