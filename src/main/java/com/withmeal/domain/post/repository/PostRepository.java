package com.withmeal.domain.post.repository;

import com.withmeal.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by Gyunny 2021/11/09
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
