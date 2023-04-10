package com.example.demo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.infrastructure.UserDetailsDao;
import com.example.demo.utils.AccessTokenGenerator;

@Service
public class AccessTokenService {
    private final AccessTokenGenerator accessTokenGenerator;

    private final UserDetailsDao userDetailsDao;

    public AccessTokenService(AccessTokenGenerator accessTokenGenerator,
                              UserDetailsDao userDetailsDao) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.userDetailsDao = userDetailsDao;
    }

    public Authentication authenticate(String accessToken) {
        if (!accessTokenGenerator.verify(accessToken)) {
            return null;
        }

        return userDetailsDao.findByAccessToken(accessToken)
                .map(userDetails ->
                        UsernamePasswordAuthenticationToken.authenticated(
                                userDetails.getUsername(),
                                userDetails.getPassword(),
                                userDetails.getAuthorities()))
                .orElse(null);
    }
}
