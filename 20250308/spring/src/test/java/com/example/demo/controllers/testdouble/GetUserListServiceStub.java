package com.example.demo.controllers.testdouble;

import java.util.List;

import com.example.demo.application.GetUserListService;
import com.example.demo.domain.User;

public class GetUserListServiceStub extends GetUserListService {
    @Override
    public List<User> getUsers() {
        return List.of(new User(1L, "test", "test", "test"));
    }
}
