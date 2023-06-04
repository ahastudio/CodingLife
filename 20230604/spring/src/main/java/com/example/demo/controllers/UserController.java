package com.example.demo.controllers;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.GetUserService;
import com.example.demo.application.SignupService;
import com.example.demo.dtos.SignupRequestDto;
import com.example.demo.dtos.SignupResultDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.exceptions.EmailAlreadyTaken;
import com.example.demo.models.User;
import com.example.demo.models.UserId;
import com.example.demo.security.AuthUser;

@RestController
@RequestMapping("/users")
public class UserController {
    private final SignupService signupService;
    private final GetUserService getUserService;

    public UserController(SignupService signupService,
                          GetUserService getUserService) {
        this.signupService = signupService;
        this.getUserService = getUserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignupResultDto signup(
            @Valid @RequestBody SignupRequestDto signupRequestDto
    ) {
        String accessToken = signupService.signup(
                signupRequestDto.email().trim(),
                signupRequestDto.name().trim(),
                signupRequestDto.password().trim()
        );

        return new SignupResultDto(accessToken);
    }

    @GetMapping("/me")
    public UserDto me(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId id = new UserId(authUser.id());
        User user = getUserService.getUser(id);
        return UserDto.of(user);
    }

    @ExceptionHandler(EmailAlreadyTaken.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String emailAlreadyTaken() {
        return "Email has already been taken";
    }
}
