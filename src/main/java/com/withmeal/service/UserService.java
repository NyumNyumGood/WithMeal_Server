package com.withmeal.service;

import com.withmeal.domain.follow.Follow;
import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.post.entity.Post;
import com.withmeal.domain.post.repository.PostRepository;
import com.withmeal.domain.shop.entity.Shop;
import com.withmeal.domain.shop.entity.ShopBookmark;
import com.withmeal.domain.shop.repository.ShopBookmarkRepository;
import com.withmeal.domain.user.entity.User;
import com.withmeal.domain.user.repository.UserRepository;
import com.withmeal.dto.request.user.SignInRequestDTO;
import com.withmeal.dto.request.user.SignupRequestDTO;
import com.withmeal.dto.response.user.UserProfileResponseDTO;
import com.withmeal.dto.response.user.UserProfileShopWantResponseDTO;
import com.withmeal.dto.response.user.UserProfileShopWentResponseDTO;
import com.withmeal.exception.user.DuplicatedNickNameException;
import com.withmeal.exception.user.PasswordNotMatchException;
import com.withmeal.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FollowRepository followRepository;
    private final ShopBookmarkRepository shopBookmarkRepository;

    @Transactional
    public Long signup(SignupRequestDTO signupRequestDTO) {
        verifyDuplicateNickname(signupRequestDTO.getNickname());
        var user = userRepository.save(signupRequestDTO.toEntity(passwordEncoder.encode(signupRequestDTO.getPassword())));
        System.out.println(user);
        return user.getId();
    }

    private void verifyDuplicateNickname(String nickname) {
        var user = userRepository.findAllByNickname(nickname);
        if (user.isPresent()) {
            throw new DuplicatedNickNameException();
        }
    }

    public Long signIn(SignInRequestDTO signInRequestDTO) {
        var user = userRepository.findAllByLoginId(signInRequestDTO.getLoginId())
                .orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(signInRequestDTO.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException();
        }

        return user.getId();
    }

    public UserProfileResponseDTO getUserProfile(Long userId) {
        var user = findOne(userId);
        return UserProfileResponseDTO.from(user, followRepository.countAllByFollowing(user), followRepository.countAllByFollower(user));
    }

    public List<UserProfileShopWentResponseDTO> getUserProfileWentShop(Long userId) {
        return postRepository.findAllByUser(findOne(userId)).stream()
                .map(UserProfileShopWentResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<UserProfileShopWantResponseDTO> getUserProfileWantShop(Long userId) {
        User user = findOne(userId);
        // 내가 북마크 한 가게 가져오기
        List<ShopBookmark> shopBookmarks = shopBookmarkRepository.findAllByUser(user);
        List<UserProfileShopWantResponseDTO> profileShopWantResponseDTOs = new ArrayList<>();

        for (Shop shop : shopBookmarks.stream().map(ShopBookmark::getShop).collect(Collectors.toList())) {
            // 내가 팔로우 하고 있는 사람들 중에 해당 가게에 리뷰를 작성한 적이 있는 유저들 조회
            List<User> users = postRepository.findAllByUserInAndShop(convertFollowToUser(user), shop).stream()
                    .map(Post::getUser)
                    .distinct()
                    .collect(Collectors.toList());
            profileShopWantResponseDTOs.add(UserProfileShopWantResponseDTO.from(shop, users));
        }

        return profileShopWantResponseDTOs;
    }

    public List<User> convertFollowToUser(User user) {
        return followRepository.findAllByFollower(user).stream()
                .map(Follow::getFollowing)
                .collect(Collectors.toList());
    }

    public User findOne(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

}
