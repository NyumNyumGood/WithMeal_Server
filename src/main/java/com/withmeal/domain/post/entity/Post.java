package com.withmeal.domain.post.entity;

import com.withmeal.domain.BaseEntity;
import com.withmeal.domain.shop.entity.Shop;
import com.withmeal.domain.user.entity.User;
import com.withmeal.domain.user.entity.UserWith;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
@Table(name = "post")
@Entity
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<PostEvaluate> postEvaluates = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<PostImages> postImages = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<UserWith> userWiths = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

}
