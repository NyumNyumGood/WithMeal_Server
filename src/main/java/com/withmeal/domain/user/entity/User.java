package com.withmeal.domain.user.entity;

import com.withmeal.domain.BaseEntity;
import com.withmeal.domain.follow.Follow;
import com.withmeal.domain.post.entity.Post;
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
import java.util.stream.Collectors;

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

    private String loginId;

    private String password;

    private String universityName;

    private String profileImage;

    private String refreshToken;

    @Builder.Default
    @OneToMany(mappedBy = "following")
    private List<Follow> following = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "follower")
    private List<Follow> follower = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<ShopBookmark> shopBookmarks = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public boolean exceptUsers(List<User> users) {
        return !users.stream().map(User::getId).collect(Collectors.toList()).contains(id);
    }

    public void changeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
