package com.example.demo.application.session;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.infrastructure.UserDetailsDao;

@Service
@Transactional
public class LogoutService {
    private final UserDetailsDao userDetailsDao;

    public LogoutService(UserDetailsDao userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }

    public void logout(String accessToken) {
        userDetailsDao.removeAccessToken(accessToken);
    }
}
