package com.withmeal.domain.post.repository;

import com.withmeal.domain.post.entity.PostBookmark;
import com.withmeal.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * created by Gyunny 2021/11/25
 */
public interface PostBookmarkRepository extends JpaRepository<PostBookmark, Long> {

    List<PostBookmark> findAllByUser(User user);
}
