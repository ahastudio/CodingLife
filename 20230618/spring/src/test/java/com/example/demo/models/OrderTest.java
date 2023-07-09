package com.example.demo.models;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;

import static com.example.demo.TestUtils.createOrderOptions;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @Test
    void creation() {
        Product product1 = Fixtures.product("맨투맨");
        Product product2 = Fixtures.product("셔츠");

        List<OrderLineItem> lineItems = List.of(
                new OrderLineItem(
                        OrderLineItemId.generate(),
                        product1,
                        createOrderOptions(product1, new int[]{0, 0}),
                        1
                ),
                new OrderLineItem(
                        OrderLineItemId.generate(),
                        product1,
                        createOrderOptions(product1, new int[]{1, 1}),
                        2
                ),
                new OrderLineItem(
                        OrderLineItemId.generate(),
                        product2,
                        List.of(),
                        2
                )
        );

        Receiver receiver = Fixtures.receiver("홍길동");
        Payment payment = Fixtures.payment();

        Order order = new Order(OrderId.generate(), UserId.generate(),
                lineItems, receiver, payment, OrderStatus.PAID);

        assertThat(order.lineItemSize()).isEqualTo(3);

        assertThat(order.totalPrice())
                .isEqualTo(new Money(128_000L + 128_000L * 2 + 123_000L * 2));
    }

    @Test
    @DisplayName("title - when line item size is 1")
    void titleWithOnlyOneLineItem() {
        Product product = Fixtures.product("맨투맨");

        List<OrderLineItem> lineItems = List.of(
                new OrderLineItem(
                        OrderLineItemId.generate(),
                        product,
                        createOrderOptions(product, new int[]{0, 0}),
                        1
                )
        );

        Receiver receiver = Fixtures.receiver("홍길동");
        Payment payment = Fixtures.payment();

        Order order = new Order(OrderId.generate(), UserId.generate(),
                lineItems, receiver, payment, OrderStatus.PAID);

        assertThat(order.lineItemSize()).isEqualTo(1);

        assertThat(order.title()).isEqualTo("맨투맨");
    }

    @Test
    @DisplayName("title - when line item size is 2")
    void titleWithTwoLineItems() {
        Product product1 = Fixtures.product("맨투맨");
        Product product2 = Fixtures.product("셔츠");

        List<OrderLineItem> lineItems = List.of(
                new OrderLineItem(
                        OrderLineItemId.generate(),
                        product1,
                        createOrderOptions(product1, new int[]{0, 0}),
                        1
                ),
                new OrderLineItem(
                        OrderLineItemId.generate(),
                        product2,
                        List.of(),
                        1
                )
        );

        Receiver receiver = Fixtures.receiver("홍길동");
        Payment payment = Fixtures.payment();

        Order order = new Order(OrderId.generate(), UserId.generate(),
                lineItems, receiver, payment, OrderStatus.PAID);

        assertThat(order.lineItemSize()).isEqualTo(2);

        assertThat(order.title()).isEqualTo("맨투맨 외 1");
    }
}
