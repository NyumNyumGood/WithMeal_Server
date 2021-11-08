package com.withmeal.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by Gyunny 2021/11/09
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
