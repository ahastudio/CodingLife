package com.example.demo.application.cart;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Cart;
import com.example.demo.models.CartId;
import com.example.demo.models.LineItemId;
import com.example.demo.repositories.CartRepository;

@Service
@Transactional
public class ChangeCartItemQuantityService {
    private final CartRepository cartRepository;

    public ChangeCartItemQuantityService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void changeQuantity(LineItemId lineItemId, int quantity) {
        Cart cart = cartRepository.findById(CartId.DEFAULT).orElseThrow();

        cart.changeLineItemQuantity(lineItemId, quantity);
    }
}
