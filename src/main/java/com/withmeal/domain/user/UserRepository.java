package com.withmeal.domain.user;

import com.withmeal.domain.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * created by Gyunny 2021/11/09
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByFollower(Follow follower);

}
