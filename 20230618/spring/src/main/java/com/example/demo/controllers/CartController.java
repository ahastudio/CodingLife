package com.example.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.GetCartService;
import com.example.demo.dtos.CartDto;
import com.example.demo.models.UserId;
import com.example.demo.security.AuthUser;
import com.example.demo.security.UserRequired;

@UserRequired
@RestController
@RequestMapping("/cart")
public class CartController {
    private final GetCartService getCartService;

    public CartController(GetCartService getCartService) {
        this.getCartService = getCartService;
    }

    @GetMapping
    public CartDto detail(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        UserId userId = new UserId(authUser.id());

        return getCartService.getCartDto(userId);
    }
}
