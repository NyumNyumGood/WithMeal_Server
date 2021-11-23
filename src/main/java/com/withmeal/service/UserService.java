package com.withmeal.service;

import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.user.User;
import com.withmeal.domain.user.UserRepository;
import com.withmeal.dto.response.user.UserProfileResponseDTO;
import com.withmeal.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by Gyunny 2021/11/24
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public UserProfileResponseDTO getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return UserProfileResponseDTO.from(user, followRepository.countAllByFollowing(user), followRepository.countAllByFollower(user));
    }

}
