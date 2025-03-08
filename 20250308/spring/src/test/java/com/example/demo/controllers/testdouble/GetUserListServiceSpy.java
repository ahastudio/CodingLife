package com.example.demo.controllers.testdouble;

import java.util.List;

import com.example.demo.application.GetUserListService;
import com.example.demo.domain.User;

public class GetUserListServiceSpy extends GetUserListService {
    private boolean getUsersWasCalled = false;

    @Override
    public List<User> getUsers() {
        getUsersWasCalled = true;
        return List.of();
    }

    public boolean getUsersWasCalled() {
        return getUsersWasCalled;
    }
}
