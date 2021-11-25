package com.withmeal.domain.shop.repository;

import com.withmeal.domain.shop.entity.ShopBookmark;
import com.withmeal.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * created by Gyunny 2021/11/25
 */
public interface ShopBookmarkRepository extends JpaRepository<ShopBookmark, Long> {

    List<ShopBookmark> findAllByUser(User user);
}
