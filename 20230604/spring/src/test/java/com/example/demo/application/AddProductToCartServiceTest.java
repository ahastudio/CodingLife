package com.example.demo.application;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AddProductToCartServiceTest {
    private AddProductToCartService addProductToCartService;

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    private Product product;
    private Set<CartLineItemOption> options;
    private int quantity;

    @BeforeEach
    void setUpMockObjects() {
        cartRepository = mock(CartRepository.class);

        productRepository = mock(ProductRepository.class);

        addProductToCartService = new AddProductToCartService(
                cartRepository, productRepository);
    }

    @BeforeEach
    void setUpFixtures() {
        product = Fixtures.product("맨투맨");

        options = Set.of(
                createCartLineItemOption(product, 0, 0),
                createCartLineItemOption(product, 1, 0)
        );

        quantity = 1;
    }

    @Test
    @DisplayName("addProductToCart - when cart exists")
    void addProductToCart() {
        UserId userId = new UserId("USER-ID");

        Cart cart = new Cart(userId);

        given(cartRepository.findByUserId(userId))
                .willReturn(Optional.of(cart));

        given(productRepository.findById(product.id()))
                .willReturn(Optional.of(product));

        addProductToCartService.addProductToCart(
                userId, product.id(), options, quantity);

        assertThat(cart.lineItemSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("addProductToCart - when cart doesn't exist")
    void addProductToCartWithoutCart() {
        UserId userId = new UserId("USER-ID");

        given(cartRepository.findByUserId(userId))
                .willReturn(Optional.empty());

        given(productRepository.findById(product.id()))
                .willReturn(Optional.of(product));

        addProductToCartService.addProductToCart(
                userId, product.id(), options, quantity);

        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    @DisplayName("addProductToCart - when product has no option")
    void addProductToCartWithoutOption() {
        UserId userId = new UserId("USER-ID");

        product = Fixtures.product("셔츠");

        options = Set.of();

        given(cartRepository.findByUserId(userId))
                .willReturn(Optional.empty());

        given(productRepository.findById(product.id()))
                .willReturn(Optional.of(product));

        addProductToCartService.addProductToCart(
                userId, product.id(), options, quantity);

        verify(cartRepository).save(any(Cart.class));
    }
}
