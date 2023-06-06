package com.example.demo.application;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;
import com.example.demo.models.Cart;
import com.example.demo.models.CartLineItemOption;
import com.example.demo.models.Product;
import com.example.demo.models.UserId;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ProductRepository;

import static com.example.demo.TestUtils.createCartLineItemOption;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AddProductToCartServiceTest {
    private AddProductToCartService addProductToCartService;

    private CartRepository cartRepository;

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        cartRepository = mock(CartRepository.class);

        productRepository = mock(ProductRepository.class);

        addProductToCartService = new AddProductToCartService(
                cartRepository, productRepository);
    }

    @Test
    void addProductToCart() {
        UserId userId = new UserId("USER-ID");

        Cart cart = new Cart(userId);

        Product product = Fixtures.product("맨투맨");

        Set<CartLineItemOption> options = Set.of(
                createCartLineItemOption(product, 0, 0),
                createCartLineItemOption(product, 1, 0)
        );

        int quantity = 1;

        given(cartRepository.findByUserId(userId))
                .willReturn(Optional.of(cart));

        given(productRepository.findById(product.id()))
                .willReturn(Optional.of(product));

        addProductToCartService.addProductToCart(
                userId, product.id(), options, quantity);

        assertThat(cart.lineItemSize()).isEqualTo(1);
    }
}
