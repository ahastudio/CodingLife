package com.example.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;

@Service
public class GetUserListService {
    public List<User> getUsers() {
        // 진짜 DB를 썼다면...
        return List.of(
            new User(1L, "소", "크라테스", "test@test.com")
        );
    }
}
