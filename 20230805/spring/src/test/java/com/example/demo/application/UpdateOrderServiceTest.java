package com.example.demo.application;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;
import com.example.demo.models.Order;
import com.example.demo.models.OrderId;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.User;
import com.example.demo.repositories.OrderRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateOrderServiceTest {
    private OrderRepository orderRepository;

    private UpdateOrderService updateOrderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);

        updateOrderService = new UpdateOrderService(orderRepository);
    }

    @Test
    void updateOrderStatus() {
        User user = Fixtures.user("tester");
        Order order = Fixtures.order(user);

        OrderId orderId = order.id();
        OrderStatus newStatus = OrderStatus.COMPLETE;

        given(orderRepository.findById(orderId)).willReturn(Optional.of(order));

        assertThat(order.status()).isNotEqualTo(newStatus);

        updateOrderService.updateOrderStatus(orderId, newStatus);

        assertThat(order.status()).isEqualTo(newStatus);
    }
}
