package com.ahastudio.user.controllers;

import java.util.Optional;

import com.ahastudio.user.exceptions.InvalidAccessToken;
import com.ahastudio.user.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/")
    public String home(
            @RequestAttribute Optional<User> user
    ) {
//        String name = user.isEmpty() ? "world" : user.get().name();
        String name = user.map(User::name).orElse("world");

        return "Hello, " + name + "!";
    }

    @ExceptionHandler(InvalidAccessToken.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidAccessToken() {
        return "Invalid access token!";
    }
}
