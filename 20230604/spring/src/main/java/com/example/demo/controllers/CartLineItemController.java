package com.example.demo.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.AddProductToCartService;
import com.example.demo.dtos.AddProductToCartDto;
import com.example.demo.models.CartLineItemOption;
import com.example.demo.models.ProductId;
import com.example.demo.models.ProductOptionId;
import com.example.demo.models.ProductOptionItemId;
import com.example.demo.models.UserId;
import com.example.demo.security.AuthUser;
import com.example.demo.security.UserRequired;

@UserRequired
@RestController
@RequestMapping("/cart/line-items")
public class CartLineItemController {
    private final AddProductToCartService addProductToCartService;

    public CartLineItemController(
            AddProductToCartService addProductToCartService) {
        this.addProductToCartService = addProductToCartService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            Authentication authentication,
            @RequestBody AddProductToCartDto requestDto) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        UserId userId = new UserId(authUser.id());
        ProductId productId = new ProductId(requestDto.productId());
        Set<CartLineItemOption> options = requestDto.options().stream()
                .map(option -> new CartLineItemOption(
                        new ProductOptionId(option.id()),
                        new ProductOptionItemId(option.itemId())
                ))
                .collect(Collectors.toSet());
        int quantity = requestDto.quantity();

        addProductToCartService.addProductToCart(
                userId, productId, options, quantity);

        return "Created";
    }
}
