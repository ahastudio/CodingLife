package com.example.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.OrderListDto;
import com.example.demo.dtos.OrderSummaryDto;
import com.example.demo.models.Order;
import com.example.demo.models.UserId;
import com.example.demo.repositories.OrderRepository;

@Service
@Transactional(readOnly = true)
public class GetOrderListService {
    private final OrderRepository orderRepository;

    public GetOrderListService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderListDto getOrderList(UserId userId) {
        List<Order> orders =
                orderRepository.findAllByUserIdOrderByIdDesc(userId);

        return new OrderListDto(
                orders.stream()
                        .map(OrderSummaryDto::of)
                        .toList()
        );
    }
}
