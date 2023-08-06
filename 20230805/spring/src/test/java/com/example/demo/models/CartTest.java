package com.example.demo.models;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;

import static com.example.demo.TestUtils.createCartLineItemOption;
import static org.assertj.core.api.Assertions.assertThat;

class CartTest {
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart(new UserId("USER-ID"));
    }

    @Test
    @DisplayName("addProduct - Add a new product")
    void addProduct() {
        Product product = Fixtures.product("맨투맨");

        Set<CartLineItemOption> options = Set.of(
                createCartLineItemOption(product, 0, 0),
                createCartLineItemOption(product, 1, 0)
        );

        cart.addProduct(product, options, 1);

        assertThat(cart.lineItemSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("addProduct - Add same product")
    void addProductDuplicated() {
        Product product = Fixtures.product("맨투맨");

        Set<CartLineItemOption> options = Set.of(
                createCartLineItemOption(product, 0, 0),
                createCartLineItemOption(product, 1, 0)
        );

        cart.addProduct(product, options, 1);
        cart.addProduct(product, options, 2);

        assertThat(cart.lineItemSize()).isEqualTo(1);

        assertThat(cart.lineItem(0).quantity()).isEqualTo(1 + 2);
    }

    @Test
    @DisplayName("addProduct - Add different product")
    void addProductDifferent() {
        Product product = Fixtures.product("맨투맨");

        Set<CartLineItemOption> options1 = Set.of(
                createCartLineItemOption(product, 0, 0),
                createCartLineItemOption(product, 1, 0)
        );

        Set<CartLineItemOption> options2 = Set.of(
                createCartLineItemOption(product, 0, 1),
                createCartLineItemOption(product, 1, 0)
        );

        cart.addProduct(product, options1, 1);
        cart.addProduct(product, options2, 2);

        assertThat(cart.lineItemSize()).isEqualTo(2);

        assertThat(cart.lineItem(0).quantity()).isEqualTo(1);
        assertThat(cart.lineItem(1).quantity()).isEqualTo(2);
    }
}
