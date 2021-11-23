package com.withmeal.service;

import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.user.User;
import com.withmeal.domain.user.UserRepository;
import com.withmeal.dto.response.follow.FollowProfileListResponseDTO;
import com.withmeal.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Gyunny 2021/11/09
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FriendService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public List<FollowProfileListResponseDTO> getFriendsProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return followRepository.findAllByFollower(user).stream()
                .map(FollowProfileListResponseDTO::from)
                .collect(Collectors.toList());
    }

}
