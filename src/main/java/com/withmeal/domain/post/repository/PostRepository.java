package com.withmeal.domain.post.repository;

import com.withmeal.domain.post.entity.Post;
import com.withmeal.domain.shop.entity.Shop;
import com.withmeal.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * created by Gyunny 2021/11/09
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select distinct p from Post p join fetch p.user left join fetch p.postEvaluates where p.user in (:users)")
    List<Post> findAllByUsersIn(List<User> users);

    List<Post> findAllByUser(User user);

    List<Post> findAllByUserInAndShop(List<User> users, Shop shop);

}
