package com.withmeal.service;

import com.withmeal.domain.BaseEntity;
import com.withmeal.domain.follow.FollowRepository;
import com.withmeal.domain.post.entity.Post;
import com.withmeal.domain.post.entity.PostImages;
import com.withmeal.domain.post.repository.PostRepository;
import com.withmeal.domain.shop.entity.Shop;
import com.withmeal.domain.user.entity.User;
import com.withmeal.domain.user.repository.UserRepository;
import com.withmeal.dto.request.user.SignInRequestDTO;
import com.withmeal.dto.request.user.SignupRequestDTO;
import com.withmeal.exception.user.DuplicatedNickNameException;
import com.withmeal.exception.user.PasswordNotMatchException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.withmeal.service.PostServiceTest.createPost;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * created by Gyunny 2021/11/26
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    FollowRepository followRepository;

    @InjectMocks
    UserService userService;

    @DisplayName("회원가입 닉네임이 중복되면 DuplicatedNickNameException 발생")
    @Test
    void testNicknameDuplicateException() {
        // given
        var signupRequestDTO = createSignupRequestDTO();
        given(userRepository.findAllByNickname("nickname")).willReturn(Optional.of(createUser(1L)));

        // when & then
        assertThatThrownBy(() -> {
            userService.signup(signupRequestDTO);
        }).isInstanceOf(DuplicatedNickNameException.class);
    }

//    @DisplayName("회원 가입이 잘 되는지 된다면 userId 반환한다.")
//    @Test
//    void testSignup() {
//        // given
//        var signupRequestDTO = createSignupRequestDTO();
//        var createUser = createUser(1L);
//        //ReflectionTestUtils.setField(createUser, "id", 1L);
//
//        given(userRepository.findAllByNickname("nickname")).willReturn(Optional.empty());
//        given(passwordEncoder.encode(signupRequestDTO.getPassword())).willReturn(anyString());
//        given(userRepository.save(signupRequestDTO.toEntity(signupRequestDTO.getPassword()))).willReturn(createUser);
//
//        // when
//        Long userId = userService.signup(signupRequestDTO);
//
//        // then
//        assertThat(userId).isEqualTo(createUser.getId());
//    }

    @DisplayName("비밀번호가 올바르지 않으면 PasswordNotMatchException 발생")
    @Test
    void testWrongPasswordTest() {
        var rowPassword = "123";
        var encodePassword = "asdasd";
        var loginId = "loginId";

        var signInRequestDTO = SignInRequestDTO.builder()
                .loginId(loginId)
                .password(rowPassword)
                .build();

        // given
        given(userRepository.findAllByLoginId(loginId)).willReturn(Optional.of(createUser(1L)));
        given(passwordEncoder.matches(rowPassword, encodePassword)).willReturn(false);

        // when & then
        assertThatThrownBy(() -> {
            userService.signIn(signInRequestDTO);
        }).isInstanceOf(PasswordNotMatchException.class);

    }

    @DisplayName("유저 상단 프로필 조회가 올바르게 작동한다.")
    @Test
    void testGetUserProfile() {
        var user = createUser(1L);

        // given
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(followRepository.countAllByFollower(user)).willReturn(1L);
        given(followRepository.countAllByFollowing(user)).willReturn(2L);

        // when
        var userProfileResponseDTO = userService.getUserProfile(1L);

        // then
        assertThat(userProfileResponseDTO.getUserId()).isEqualTo(user.getId());
        assertThat(userProfileResponseDTO.getNickname()).isEqualTo(user.getNickname());
        assertThat(userProfileResponseDTO.getImageUrl()).isEqualTo(user.getProfileImage());
        assertThat(userProfileResponseDTO.getUniversityName()).isEqualTo(user.getUniversityName());
        assertThat(userProfileResponseDTO.getFollowerCount()).isEqualTo(2L);
        assertThat(userProfileResponseDTO.getFollowingCount()).isEqualTo(1L);
    }

    @DisplayName("유저 프로필 가봤어요 조회할 수 있다.")
    @Test
    void testGetUserProfileWentShop() {
        List<Post> posts = new ArrayList<>();
        posts.add(createPost());

        var user = createUser(1L);
        ReflectionTestUtils.setField(
                posts.get(0),
                BaseEntity.class,
                "createdTime",
                LocalDateTime.of(2021, 12, 6, 12, 6),
                LocalDateTime.class
        );

        // given
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(postRepository.findAllByUser(user)).willReturn(posts);

        // when
        var userProfileWentShops = userService.getUserProfileWentShop(1L);

        // then
        var shopWentResponseDTO = userProfileWentShops.get(0);
        assertThat(shopWentResponseDTO.getShopId()).isEqualTo(posts.get(0).getShop().getId());
        assertThat(shopWentResponseDTO.getShopName()).isEqualTo(posts.get(0).getShop().getShopName());
        assertThat(shopWentResponseDTO.getShopImage()).isEqualTo(posts.get(0).getShop().getPostImages().stream().map(PostImages::getImageUrl).collect(Collectors.toList()));
        assertThat(shopWentResponseDTO.getCreatedAt()).isEqualTo(posts.get(0).getCreatedTime().toLocalDate());
    }

    public static User createUser(Long userId) {
        return User.builder()
                .id(userId)
                .nickname("Gyunny")
                .loginId("login")
                .password("123")
                .universityName("대학교")
                .profileImage("profile")
                .refreshToken("RefreshToken")
                .build();
    }

    public static Shop createShop() {
        return Shop.builder()
                .id(1L)
                .shopName("Shop")
                .category("한식")
                .shopTelephone("010-1234-5678")
                .postImages(List.of(createPostImages()))
                .build();
    }

    private static PostImages createPostImages() {
        return PostImages.builder()
                .imageUrl("image1")
                .build();
    }

    public static SignupRequestDTO createSignupRequestDTO() {
        return SignupRequestDTO.builder()
                .loginId("loginId")
                .password("1234")
                .nickname("nickname")
                .build();
    }

}