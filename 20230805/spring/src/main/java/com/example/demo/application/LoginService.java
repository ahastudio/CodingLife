package com.example.demo.application;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.security.AccessTokenGenerator;
import com.example.demo.security.AuthUser;
import com.example.demo.security.AuthUserDao;

@Service
@Transactional
public class LoginService {
    private final AuthUserDao authUserDao;

    private final PasswordEncoder passwordEncoder;

    private final AccessTokenGenerator accessTokenGenerator;

    public LoginService(AuthUserDao authUserDao,
                        PasswordEncoder passwordEncoder,
                        AccessTokenGenerator accessTokenGenerator) {
        this.authUserDao = authUserDao;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenGenerator = accessTokenGenerator;
    }

    public String login(String email, String password) {
        return authUserDao.findByEmail(email)
                .filter(authUser -> matchPassword(authUser, password))
                .map(this::generateAccessToken)
                .orElseThrow(() -> new BadCredentialsException("Login failed"));
    }

    public String loginAdmin(String email, String password) {
        return authUserDao.findByEmail(email)
                .filter(authUser -> matchPassword(authUser, password))
                .filter(AuthUser::isAdmin)
                .map(this::generateAccessToken)
                .orElseThrow(() -> new BadCredentialsException("Login failed"));
    }

    private boolean matchPassword(AuthUser authUser, String password) {
        return passwordEncoder.matches(password, authUser.password());
    }

    private String generateAccessToken(AuthUser authUser) {
        String id = authUser.id();
        String accessToken = accessTokenGenerator.generate(id);
        authUserDao.addAccessToken(id, accessToken);
        return accessToken;
    }
}
