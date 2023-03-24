package com.example.demo.models;

import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;

import static org.assertj.core.api.Assertions.assertThat;

class LineItemTest {
    @Test
    void creation() {
        Product product = Fixtures.product();
        ProductId productId = product.id();

        LineItem lineItem = LineItem.create(product, 2);

        assertThat(lineItem.sameProduct(productId)).isTrue();

        assertThat(lineItem.totalPrice()).isEqualTo(product.price().times(2));
    }

    @Test
    void increaseQuantity() {
        Product product = Fixtures.product();

        LineItem lineItem = LineItem.create(product, 2);

        lineItem.increaseQuantity(3);

        assertThat(lineItem.quantity()).isEqualTo(5);
        assertThat(lineItem.totalPrice()).isEqualTo(product.price().times(5));
    }

    @Test
    void changeQuantity() {
        Product product = Fixtures.product();

        LineItem lineItem = LineItem.create(product, 2);

        lineItem.changeQuantity(3);

        assertThat(lineItem.quantity()).isEqualTo(3);
        assertThat(lineItem.totalPrice()).isEqualTo(product.price().times(3));
    }
}
