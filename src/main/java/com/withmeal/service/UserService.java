package com.withmeal.service;

import com.withmeal.domain.follow.Follow;
import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.post.entity.Post;
import com.withmeal.domain.post.entity.PostBookmark;
import com.withmeal.domain.post.repository.PostBookmarkRepository;
import com.withmeal.domain.post.repository.PostRepository;
import com.withmeal.domain.shop.Shop;
import com.withmeal.domain.user.User;
import com.withmeal.domain.user.UserRepository;
import com.withmeal.dto.response.user.UserProfileResponseDTO;
import com.withmeal.dto.response.user.UserProfileShopWantResponseDTO;
import com.withmeal.dto.response.user.UserProfileShopWentResponseDTO;
import com.withmeal.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final PostBookmarkRepository shopBookmarkRepository;

    public UserProfileResponseDTO getUserProfile(Long userId) {
        User user = findOne(userId);
        return UserProfileResponseDTO.from(user, followRepository.countAllByFollowing(user), followRepository.countAllByFollower(user));
    }

    public List<UserProfileShopWentResponseDTO> getUserProfileWentShop(Long userId) {
        return postRepository.findAllByUser(findOne(userId)).stream()
                .map(UserProfileShopWentResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<UserProfileShopWantResponseDTO> getUserProfileWantShop(Long userId) {
        // 내가 북마크 한 게시글 가져오기
        User user = findOne(userId);
        List<PostBookmark> postBookmarks = shopBookmarkRepository.findAllByUser(user);
        List<UserProfileShopWantResponseDTO> profileShopWantResponseDTOs = new ArrayList<>();

        for (Post post : postBookmarks.stream().map(PostBookmark::getPost).collect(Collectors.toList())) {
            // 내가 팔로우 하고 있는 사람들 중에 해당 가게에 리뷰를 작성한 적이 있는 유저들 조회
            List<User> users = postRepository.findAllByUserInAndShop(convertFollowToUser(user), post.getShop()).stream()
                    .map(Post::getUser)
                    .distinct()
                    .collect(Collectors.toList());
            profileShopWantResponseDTOs.add(UserProfileShopWantResponseDTO.from(post, users));
        }

        return profileShopWantResponseDTOs;
    }

    private List<User> convertFollowToUser(User user) {
        return followRepository.findAllByFollower(user).stream()
                .map(Follow::getFollowing)
                .collect(Collectors.toList());
    }

    private User findOne(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

}
