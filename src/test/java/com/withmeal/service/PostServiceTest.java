package com.withmeal.service;

import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.post.repository.PostRepository;
import com.withmeal.domain.user.repository.UserRepository;
import com.withmeal.exception.user.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * created by Gyunny 2021/11/26
 */
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    FollowRepository followRepository;

    @InjectMocks
    PostService postService;

    @DisplayName("존재하지 않는 유저일 때 UserNotFoundException 발생")
    @Test
    void testExistNotUserException() {
        assertThatThrownBy(() -> {
            postService.getHomeFeed(anyLong());
        }).isInstanceOf(UserNotFoundException.class);
    }

    @DisplayName("홈 화면 피드 조회 테스트")
    @Test
    void testHomeFeed() {

        // given
    }
}