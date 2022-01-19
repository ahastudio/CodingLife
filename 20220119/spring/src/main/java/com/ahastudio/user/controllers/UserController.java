package com.ahastudio.user.controllers;

import com.ahastudio.user.application.UserService;
import com.ahastudio.user.dtos.UserCreatedDto;
import com.ahastudio.user.dtos.UserRegistrationDto;
import com.ahastudio.user.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreatedDto register(
            @RequestBody UserRegistrationDto userRegistrationDto
    ) {
        User user = userService.create(userRegistrationDto);
        return user.toCreatedDto();
    }
}
