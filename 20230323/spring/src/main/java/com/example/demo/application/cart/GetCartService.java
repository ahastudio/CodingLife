package com.example.demo.application.cart;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controllers.dtos.CartDto;
import com.example.demo.infrastructure.CartDtoFetcher;
import com.example.demo.models.CartId;

@Service
@Transactional(readOnly = true)
public class GetCartService {
    private final CartDtoFetcher cartDtoFetcher;

    public GetCartService(CartDtoFetcher cartDtoFetcher) {
        this.cartDtoFetcher = cartDtoFetcher;
    }

    public CartDto getCartDto() {
        return cartDtoFetcher.fetchCartDto(CartId.DEFAULT);
    }
}
