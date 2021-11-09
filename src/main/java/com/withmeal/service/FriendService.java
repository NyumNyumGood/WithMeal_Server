package com.withmeal.service;

import com.withmeal.domain.follow.Follow;
import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.user.User;
import com.withmeal.domain.user.UserRepository;
import com.withmeal.dto.response.follow.FollowProfileListResponseDTO;
import com.withmeal.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        User user1 = User.builder()
                .nickname("Gyunny1")
                .profileImage("http://www.naver.com")
                .universityName("서울 과기대")
                .build();

        User user2 = User.builder()
                .nickname("Gyunny2")
                .profileImage("http://www.naver.com")
                .universityName("외대")
                .build();

        User user3 = User.builder()
                .nickname("Gyunny3")
                .profileImage("http://www.naver.com")
                .universityName("서울대")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Follow follow1 = Follow.builder()
                .follower(user1)
                .following(user2)
                .build();

        Follow follow2 = Follow.builder()
                .follower(user1)
                .following(user3)
                .build();

        followRepository.save(follow1);
        followRepository.save(follow2);
    }

    public List<FollowProfileListResponseDTO> getFriendsProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return followRepository.findAllByFollower(user).stream()
                .map(FollowProfileListResponseDTO::from)
                .collect(Collectors.toList());
    }

}
