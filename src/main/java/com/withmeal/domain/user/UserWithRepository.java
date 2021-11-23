package com.withmeal.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by Gyunny 2021/11/24
 */
public interface UserWithRepository extends JpaRepository<UserWith, Long> {
}
