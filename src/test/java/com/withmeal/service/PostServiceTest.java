package com.withmeal.service;

import com.withmeal.domain.follow.Follow;
import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.post.entity.Post;
import com.withmeal.domain.post.repository.PostRepository;
import com.withmeal.domain.user.repository.UserRepository;
import com.withmeal.exception.user.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static com.withmeal.service.UserServiceTest.createShop;
import static com.withmeal.service.UserServiceTest.createUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

/**
 * created by Gyunny 2021/11/26
 */
@MockitoSettings(strictness = Strictness.LENIENT)
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

    // 테스트 다시
    @DisplayName("홈 화면 피드 조회 Service 테스트")
    @Test
    void testHomeFeed() {
        // given
        var post = createPost();
        var user = createUser(1L);
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(followRepository.findAllByFollower(user)).willReturn(List.of(createFollow()));
        given(postRepository.findAllByUsersIn(List.of(user))).willReturn(List.of(post));

        // when
        var homeFeeds = postService.getHomeFeed(1L);

        var postFeedResponseDTO = homeFeeds.get(0);
        assertThat(postFeedResponseDTO.getPostId()).isEqualTo(post.getId());
        assertThat(postFeedResponseDTO.getTitle()).isEqualTo(post.getTitle());
        assertThat(postFeedResponseDTO.getContent()).isEqualTo(post.getContent());
    }

    public static Post createPost() {
        return Post.builder()
                .id(1L)
                .title("제목")
                .content("내용")
                .shop(createShop())
                .build();
    }

    public static Follow createFollow() {
        return Follow.builder()
                .id(1L)
                .follower(createUser(1L))
                .following(createUser(2L))
                .build();
    }
}