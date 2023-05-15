package com.example.demo.security;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenService {
    private final AccessTokenGenerator accessTokenGenerator;
    private final AuthUserDao authUserDao;

    public AccessTokenService(AccessTokenGenerator accessTokenGenerator,
                              AuthUserDao authUserDao) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.authUserDao = authUserDao;
    }

    public Authentication authenticate(String accessToken) {
        if (!accessTokenGenerator.verify(accessToken)) {
            return null;
        }

        return authUserDao.findByAccessToken(accessToken)
                .map(authUser ->
                        UsernamePasswordAuthenticationToken.authenticated(
                                authUser, null, List.of(authUser::role)))
                .orElse(null);
    }
}
