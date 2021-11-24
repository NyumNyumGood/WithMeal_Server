package com.withmeal.service;

import com.withmeal.domain.follow.Follow;
import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.post.repository.PostRepository;
import com.withmeal.domain.user.User;
import com.withmeal.domain.user.UserRepository;
import com.withmeal.dto.response.post.PostResponseDTO;
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
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public List<PostResponseDTO> getHomeFeed(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return postRepository.findAllByUsersIn(convertFollowToUser(user)).stream()
                .map(PostResponseDTO::from)
                .collect(Collectors.toList());
    }

    private List<User> convertFollowToUser(User user) {
        return followRepository.findAllByFollower(user).stream()
                .map(Follow::getFollowing)
                .collect(Collectors.toList());
    }

}
