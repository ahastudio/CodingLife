package com.example.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class GetUserListService {
    private final UserRepository userRepository;

    public GetUserListService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList() {
        return userRepository.findAllByOrderByIdDesc();
    }
}
