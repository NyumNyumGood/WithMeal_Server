package com.withmeal.service;

import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.post.repository.PostRepository;
import com.withmeal.domain.user.User;
import com.withmeal.domain.user.UserRepository;
import com.withmeal.dto.response.user.UserProfileResponseDTO;
import com.withmeal.dto.response.user.UserProfileWentShopResponseDTO;
import com.withmeal.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Gyunny 2021/11/24
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FollowRepository followRepository;

    public UserProfileResponseDTO getUserProfile(Long userId) {
        User user = findOne(userId);
        return UserProfileResponseDTO.from(user, followRepository.countAllByFollowing(user), followRepository.countAllByFollower(user));
    }

    public List<UserProfileWentShopResponseDTO> getUserProfileWentShop(Long userId) {
        return postRepository.findAllByUser(findOne(userId)).stream()
                .map(UserProfileWentShopResponseDTO::from)
                .collect(Collectors.toList());
    }

    private User findOne(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
