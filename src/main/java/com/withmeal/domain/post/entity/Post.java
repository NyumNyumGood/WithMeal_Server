package com.withmeal.domain.post.entity;

import com.withmeal.domain.BaseEntity;
import com.withmeal.domain.shop.Shop;
import com.withmeal.domain.user.User;
import com.withmeal.domain.user.UserWith;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Gyunny 2021/11/09
 */
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")
@Entity
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "post")
    private List<PostEvaluate> postEvaluates = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostImages> postImages = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<UserWith> userWiths = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

}
