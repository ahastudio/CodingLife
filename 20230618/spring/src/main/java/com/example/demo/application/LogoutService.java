package com.example.demo.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.security.AuthUserDao;

@Service
@Transactional
public class LogoutService {
    private final AuthUserDao authUserDao;

    public LogoutService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    public void logout(String accessToken) {
        authUserDao.removeAccessToken(accessToken);
    }
}
