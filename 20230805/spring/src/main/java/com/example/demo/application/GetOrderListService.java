package com.example.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.OrderListDto;
import com.example.demo.dtos.OrderSummaryDto;
import com.example.demo.dtos.admin.AdminOrderListDto;
import com.example.demo.dtos.admin.AdminOrderSummaryDto;
import com.example.demo.models.Order;
import com.example.demo.models.User;
import com.example.demo.models.UserId;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class GetOrderListService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public GetOrderListService(OrderRepository orderRepository,
                               UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
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

    public AdminOrderListDto getAdminOrderList() {
        List<Order> orders = orderRepository.findAllByOrderByIdDesc();
        List<UserId> userIds = orders.stream()
                .map(Order::userId)
                .toList();
        List<User> users = userRepository.findAllByIdIn(userIds);

        return new AdminOrderListDto(
                orders.stream()
                        .map(order -> {
                            User user = users.stream()
                                    .filter(u -> u.id().equals(order.userId()))
                                    .findFirst()
                                    .orElseThrow();
                            return AdminOrderSummaryDto.of(order, user);
                        })
                        .toList()
        );
    }
}
