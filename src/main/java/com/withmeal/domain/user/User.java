package com.withmeal.domain.user;

import com.withmeal.domain.BaseEntity;
import com.withmeal.domain.follow.Follow;
import com.withmeal.domain.shop.entity.ShopBookmark;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Gyunny 2021/11/09
 */
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "user")
@Entity
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String profileImage;

    private String universityName;

    @OneToMany(mappedBy = "following")
    private List<Follow> following = new ArrayList<>();

    @OneToMany(mappedBy = "follower")
    private List<Follow> follower = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ShopBookmark> postBookmarks = new ArrayList<>();

}
