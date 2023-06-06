package com.example.demo.application;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Cart;
import com.example.demo.models.CartId;
import com.example.demo.models.CartLineItemOption;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import com.example.demo.models.UserId;
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

    public void addProductToCart(UserId userId, ProductId productId,
                                 Set<CartLineItemOption> options, int quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElse(new Cart(CartId.generate(), userId));

        Product product = productRepository.findById(productId)
                .orElseThrow();

        cart.addProduct(product, options, quantity);

        cartRepository.save(cart);
    }
}
