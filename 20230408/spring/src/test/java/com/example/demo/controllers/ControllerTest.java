package com.example.demo.controllers;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.DemoApplication;
import com.example.demo.infrastructor.UserDetailsDao;
import com.example.demo.security.AccessTokenService;
import com.example.demo.security.WebSecurityConfig;
import com.example.demo.utils.AccessTokenGenerator;

import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {
        DemoApplication.class,
        WebSecurityConfig.class,
})
public abstract class ControllerTest {
    protected static final String USER_ID = "UserId";
    protected static final String ADMIN_ID = "AdminId";

    @SpyBean
    private AccessTokenService accessTokenService;

    @SpyBean
    protected AccessTokenGenerator accessTokenGenerator;

    @MockBean
    protected UserDetailsDao userDetailsDao;

    protected String userAccessToken;

    protected String adminAccessToken;

    @BeforeEach
    void setUpAccessTokenAndUserDetailsDaoForAuthentication() {
        userAccessToken = accessTokenGenerator.generate(USER_ID);
        adminAccessToken = accessTokenGenerator.generate(ADMIN_ID);

        UserDetails user = User.withUsername(USER_ID)
                .password(userAccessToken)
                .authorities("ROLE_USER")
                .build();

        given(userDetailsDao.findByAccessToken(userAccessToken))
                .willReturn(Optional.of(user));

        UserDetails admin = User.withUsername(ADMIN_ID)
                .password(adminAccessToken)
                .authorities("ROLE_ADMIN")
                .build();

        given(userDetailsDao.findByAccessToken(adminAccessToken))
                .willReturn(Optional.of(admin));
    }
}
