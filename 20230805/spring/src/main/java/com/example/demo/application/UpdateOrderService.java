package com.example.demo.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Order;
import com.example.demo.models.OrderId;
import com.example.demo.models.OrderStatus;
import com.example.demo.repositories.OrderRepository;

@Service
@Transactional
public class UpdateOrderService {
    private final OrderRepository orderRepository;

    public UpdateOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void updateOrderStatus(OrderId orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow();

        order.changeStatus(status);
    }
}
