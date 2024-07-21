package com.example.demo.repositories;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Cart;

@Repository
public class CartRepository {
    private final Cart cart;

    public CartRepository() {
        cart = new Cart();
    }

    public Cart find() {
        return cart;
    }
}
