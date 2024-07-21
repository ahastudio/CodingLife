package com.example.demo.models;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {
    @Test
    @DisplayName("addProduct - when cart is empty")
    void addProduct() {
        Cart cart = new Cart();

        assertThat(cart.lineItemsCount()).isEqualTo(0);

        Long productId = 1L;
        int quantity = 2;

        cart.addProduct(productId, quantity);

        assertThat(cart.lineItemsCount()).isEqualTo(1);
        assertThat(cart.lineItem(0).quantity()).isEqualTo(2);
    }

    @Test
    @DisplayName("addProduct - when cart hasn't same product")
    void addProductWithDifferenceProduct() {
        Cart cart = new Cart();

        assertThat(cart.lineItemsCount()).isEqualTo(0);

        cart.addProduct(1L, 2);

        assertThat(cart.lineItemsCount()).isEqualTo(1);
        assertThat(cart.lineItem(0).quantity()).isEqualTo(2);

        cart.addProduct(2L, 3);

        assertThat(cart.lineItemsCount()).isEqualTo(2);
        assertThat(cart.lineItem(0).quantity()).isEqualTo(2);
        assertThat(cart.lineItem(1).quantity()).isEqualTo(3);
    }

    @Test
    @DisplayName("addProduct - when cart has same product")
    void addProductWithSameProduct() {
        Cart cart = new Cart();

        assertThat(cart.lineItemsCount()).isEqualTo(0);

        cart.addProduct(1L, 2);

        assertThat(cart.lineItemsCount()).isEqualTo(1);
        assertThat(cart.lineItem(0).quantity()).isEqualTo(2);

        cart.addProduct(1L, 3);

        assertThat(cart.lineItemsCount()).isEqualTo(1);
        assertThat(cart.lineItem(0).quantity()).isEqualTo(2 + 3);
    }

    @Test
    @DisplayName("addProduct - when quantity isn't positive")
    void addProductWithNonPositiveQuantity() {
        Cart cart = new Cart();

        assertThat(cart.lineItemsCount()).isEqualTo(0);

        Stream.of(0, -10).forEach(quantity -> {
            Long productId = 1L;

            cart.addProduct(productId, quantity);

            assertThat(cart.lineItemsCount()).isEqualTo(0);
        });
    }
}
