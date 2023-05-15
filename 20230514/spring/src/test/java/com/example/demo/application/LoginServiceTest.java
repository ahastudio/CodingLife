package com.example.demo.application;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.security.AccessTokenGenerator;
import com.example.demo.security.AuthUser;
import com.example.demo.security.AuthUserDao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LoginServiceTest {
    private LoginService loginService;

    private AuthUserDao authUserDao;

    private PasswordEncoder passwordEncoder;

    private AccessTokenGenerator accessTokenGenerator;

    @BeforeEach
    void setUp() {
        authUserDao = mock(AuthUserDao.class);

        passwordEncoder = mock(PasswordEncoder.class);

        accessTokenGenerator = new AccessTokenGenerator("SECRET");

        loginService = new LoginService(
                authUserDao, passwordEncoder, accessTokenGenerator);
    }

    @Test
    @DisplayName("login - with correct email and password")
    void loginSuccess() {
        String userId = "USER-ID";
        String email = "tester@example.com";
        String password = "password";
        String encodedPassword = "ENCODED-PASSWORD";

        AuthUser authUser = AuthUser.of(
                userId, email, encodedPassword, "ROLE_USER");

        given(authUserDao.findByEmail(email))
                .willReturn(Optional.of(authUser));
        given(passwordEncoder.matches(password, encodedPassword))
                .willReturn(true);

        String accessToken = loginService.login(email, password);

        verify(authUserDao).addAccessToken(eq(userId), any());

        assertThat(accessToken).isNotBlank();
    }

    @Test
    @DisplayName("login - with incorrect email")
    void loginWithIncorrectEmail() {
        String email = "xxx";
        String password = "password";

        given(authUserDao.findByEmail(email)).willReturn(Optional.empty());

        assertThatThrownBy(() -> {
            loginService.login(email, password);
        }).isInstanceOf(BadCredentialsException.class);
    }

    @Test
    @DisplayName("login - with incorrect password")
    void loginWIthIncorrectPassword() {
        String userId = "USER-ID";
        String email = "tester@example.com";
        String password = "xxx";
        String encodedPassword = "ENCODED-PASSWORD";

        AuthUser authUser = AuthUser.of(
                userId, email, encodedPassword, "ROLE_USER");

        given(authUserDao.findByEmail(email))
                .willReturn(Optional.of(authUser));
        given(passwordEncoder.matches(password, encodedPassword))
                .willReturn(false);

        assertThatThrownBy(() -> {
            loginService.login(email, password);
        }).isInstanceOf(BadCredentialsException.class);
    }
}
