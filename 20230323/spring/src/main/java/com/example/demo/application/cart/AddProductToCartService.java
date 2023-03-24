package com.example.demo.application.cart;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Cart;
import com.example.demo.models.CartId;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ProductRepository;

@Service
@Transactional
public class AddProductToCartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddProductToCartService(CartRepository cartRepository,
                                   ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart addProduct(ProductId productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow();

        Cart cart = cartRepository.findById(CartId.DEFAULT)
                .orElse(new Cart(CartId.DEFAULT));

        cart.addProduct(product, quantity);

        cartRepository.save(cart);

        return cart;
    }
}
