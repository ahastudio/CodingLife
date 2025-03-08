package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.GetUserListService;
import com.example.demo.controllers.dto.UserListDto;
import com.example.demo.domain.User;

// User라는 리소스를 CRUD하는 걸 여기에 모았다.
// UserController는 OOP가 아님.
@RestController
@RequestMapping("/users")
public class UserController {
    private final GetUserListService getUserListService;

    public UserController(GetUserListService getUserListService) {
        this.getUserListService = getUserListService;
    }

    @GetMapping
    public UserListDto list() {
        List<User> users = getUserListService.getUsers();

        return new UserListDto(
            users.stream().map(user ->
                new UserListDto.UserDto(
                    user.id(),
                    user.lastName() + user.firstName(),
                    user.email()
                )
            ).toList()
        );
    }

    // detail

    // create

    // update

    // delete
}
