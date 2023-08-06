package com.example.demo.application;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;
import com.example.demo.dtos.admin.AdminOrderDetailDto;
import com.example.demo.models.Order;
import com.example.demo.models.User;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetAdminOrderDetailServiceTest {
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    private GetAdminOrderDetailService getAdminOrderDetailService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        userRepository = mock(UserRepository.class);

        getAdminOrderDetailService = new GetAdminOrderDetailService(
                orderRepository, userRepository);
    }

    @Test
    void getOrderDetail() {
        User user = Fixtures.user("tester");
        Order order = Fixtures.order(user);

        given(orderRepository.findById(order.id()))
                .willReturn(Optional.of(order));
        given(userRepository.findById(user.id()))
                .willReturn(Optional.of(user));

        AdminOrderDetailDto orderDto =
                getAdminOrderDetailService.getOrderDetail(order.id());

        assertThat(orderDto.lineItems()).hasSize(1);
    }
}
