package com.example.demo.models;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CartTest {
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = Cart.create();
    }

    @Test
    @DisplayName("addProduct - when product is not in the cart")
    void addNewProduct() {
        Product product1 = Fixtures.product(1);
        Product product2 = Fixtures.product(2);

        cart.addProduct(product1, 1);

        assertThat(cart.lineItemsSize()).isEqualTo(1);

        cart.addProduct(product2, 1);

        assertThat(cart.lineItemsSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("addProduct - when product is already in the cart")
    void addSameProduct() {
        Product product = Fixtures.product();

        cart.addProduct(product, 1);

        assertThat(cart.lineItemsSize()).isEqualTo(1);

        cart.addProduct(product, 1);

        assertThat(cart.lineItemsSize()).isEqualTo(1);

        LineItem lineItem = cart.findLineItem(product.id()).get();

        cart.changeLineItemQuantity(lineItem.id(), 2);
    }

    @Test
    @DisplayName("addProduct - when product is not in the cart" +
            " and quantity is not positive")
    void addProductWithInvalidQuantity() {
        Product product = Fixtures.product();

        cart.addProduct(product, 0);

        assertThat(cart.lineItemsSize()).isEqualTo(0);
    }

    @Test
    @DisplayName("addProduct - when product is already in the cart" +
            " and quantity is not positive")
    void addProductWithSameProductAndInvalidQuantity() {
        Product product = Fixtures.product();

        cart.addProduct(product, 1);

        LineItem lineItem = cart.findLineItem(product.id()).get();

        cart.addProduct(product, -1);

        cart.changeLineItemQuantity(lineItem.id(), 1);
    }

    @Test
    @DisplayName("changeLineItemQuantity - with correct ID")
    void changeItemQuantity() {
        Product product = Fixtures.product();

        cart.addProduct(product, 1);

        LineItem lineItem = cart.findLineItem(product.id()).get();

        cart.changeLineItemQuantity(lineItem.id(), 3);

        assertThat(lineItem.quantity()).isEqualTo(3);
    }

    @Test
    @DisplayName("changeLineItemQuantity - with incorrect ID")
    void changeItemQuantityWithIncorrectID() {
        assertThatThrownBy(() -> {
            cart.changeLineItemQuantity(new LineItemId("BAD"), 3);
        });
    }

    @Test
    @DisplayName("changeLineItemQuantity - when quantity is not positive")
    void changeItemQuantityWithNotPositiveQuantity() {
        Product product = Fixtures.product();

        cart.addProduct(product, 1);

        LineItem lineItem = cart.findLineItem(product.id()).get();

        cart.changeLineItemQuantity(lineItem.id(), 0);

        Optional<LineItem> found = cart.findLineItem(product.id());

        assertThat(found.isEmpty()).isTrue();
    }
}
