package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.dtos.CartLineItemAdditionDto;
import com.example.demo.models.Cart;
import com.example.demo.repositories.CartRepository;

@RestController
@RequestMapping("/cart-line-items")
public class CartLineItemController {
    private final CartRepository cartRepository;

    public CartLineItemController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postCartLineItem(
            @RequestBody CartLineItemAdditionDto cartLineItemAdditionDto
    ) {
        Cart cart = cartRepository.find();

        cart.addProduct(
                cartLineItemAdditionDto.productId(),
                cartLineItemAdditionDto.quantity()
        );
    }
}
