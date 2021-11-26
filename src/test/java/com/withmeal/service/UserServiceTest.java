package com.withmeal.service;

import com.withmeal.domain.user.entity.User;
import com.withmeal.domain.user.repository.UserRepository;
import com.withmeal.dto.request.user.SignupRequestDTO;
import com.withmeal.exception.user.DuplicatedNickNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * created by Gyunny 2021/11/26
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @DisplayName("회원가입 닉네임이 중복되면 DuplicatedNickNameException 발생")
    @Test
    void testNicknameDuplicateException() {
        // given
        var loginId = "loginId";
        var password = "1234";
        var nickname = "nickname";
        SignupRequestDTO signupRequestDTO = SignupRequestDTO.builder()
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .build();

        // when & then
        assertThatThrownBy(() -> {
            userService.signup(signupRequestDTO);
        }).isInstanceOf(DuplicatedNickNameException.class);
    }
}