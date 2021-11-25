package com.withmeal.service;

import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.user.entity.User;
import com.withmeal.domain.user.repository.UserRepository;
import com.withmeal.dto.response.follow.FollowProfileListResponseDTO;
import com.withmeal.dto.response.user.UserSearchResponseDTO;
import com.withmeal.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Gyunny 2021/11/25
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FriendService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public List<FollowProfileListResponseDTO> getFriendsProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return followRepository.findAllByFollower(user).stream()
                .map(FollowProfileListResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<UserSearchResponseDTO> getFriendsSearch(Pageable pageable, String nickname, Long userId) {
        User findUser = userService.findOne(userId);
        return userRepository.findAllByNicknameContainingAndUniversityName(nickname, findUser.getUniversityName(), pageable).stream()
                .filter(user -> user.exceptUsers(List.of(findUser)))
                .map(UserSearchResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<UserSearchResponseDTO> getFriendsDefaultSearch(Pageable pageable, Long userId) {
        User findUser = userService.findOne(userId);
        return userRepository.findAllByUniversityName(findUser.getUniversityName(), pageable).stream()
                .filter(user -> user.exceptUsers(getExceptUsers(findUser)))
                .map(UserSearchResponseDTO::from)
                .collect(Collectors.toList());
    }

    private List<User> getExceptUsers(User findUser) {
        List<User> followingUsers = userService.convertFollowToUser(findUser);
        followingUsers.add(findUser);
        return followingUsers;
    }

}
