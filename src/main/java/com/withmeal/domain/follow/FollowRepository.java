package com.withmeal.domain.follow;

import com.withmeal.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * created by Gyunny 2021/11/09
 */
public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT f FROM Follow f join fetch f.follower WHERE f.follower =:follower")
    List<Follow> findAllByFollower(User follower);

    Long countAllByFollower(User follower);

    Long countAllByFollowing(User following);

}
