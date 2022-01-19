package com.ahastudio.user.application;

import com.ahastudio.user.exceptions.LoginFailed;
import com.ahastudio.user.models.User;
import com.ahastudio.user.respositories.UserRepository;
import com.ahastudio.user.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AuthenticationServiceTest {
    @Test
    void authenticate() {
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        User user = new User();
        user.changePassword(passwordEncoder, "test");

        UserRepository userRepository = mock(UserRepository.class);
        given(userRepository.getByEmail("tester@example.com")).willReturn(user);

        JwtUtil jwtUtil = new JwtUtil("MySecret");

        AuthenticationService authenticationService =
                new AuthenticationService(
                        userRepository, passwordEncoder, jwtUtil);

        String token = authenticationService.authenticate(
                "tester@example.com", "test");
        assertThat(token).isNotBlank();

        assertThrows(LoginFailed.class, () -> {
            authenticationService.authenticate(null, "test");
        });

        assertThrows(LoginFailed.class, () -> {
            authenticationService.authenticate("", "test");
        });

        assertThrows(LoginFailed.class, () -> {
            authenticationService.authenticate("xxx", "test");
        });

        assertThrows(LoginFailed.class, () -> {
            authenticationService.authenticate("tester@example.com", "xxx");
        });
    }
}