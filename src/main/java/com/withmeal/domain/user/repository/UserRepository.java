package com.withmeal.domain.user.repository;

import com.withmeal.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * created by Gyunny 2021/11/09
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByNicknameContainingAndUniversityName(String nickname, String universityName, Pageable pageable);
    List<User> findAllByUniversityName(String universityName, Pageable pageable);
    Optional<User> findAllByNickname(String nickname);
    Optional<User> findAllByLoginId(String loginId);

}
