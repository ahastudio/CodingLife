package com.example.demo.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.security.AuthUserDao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LogoutServiceTest {
    private LogoutService logoutService;

    private AuthUserDao authUserDao;

    @BeforeEach
    void setUp() {
        authUserDao = mock(AuthUserDao.class);

        logoutService = new LogoutService(authUserDao);
    }

    @Test
    void logout() {
        String accessToken = "Tester.Access.Token";

        logoutService.logout(accessToken);

        verify(authUserDao).removeAccessToken(accessToken);
    }
}
