package com.example.demo.application;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;
import com.example.demo.dtos.OrderListDto;
import com.example.demo.dtos.admin.AdminOrderListDto;
import com.example.demo.models.Order;
import com.example.demo.models.OrderId;
import com.example.demo.models.OrderLineItem;
import com.example.demo.models.OrderLineItemId;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.Payment;
import com.example.demo.models.Product;
import com.example.demo.models.Receiver;
import com.example.demo.models.User;
import com.example.demo.models.UserId;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;

import static com.example.demo.TestUtils.createOrderOptions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetOrderListServiceTest {
    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private GetOrderListService getOrderListService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        userRepository = mock(UserRepository.class);

        getOrderListService = new GetOrderListService(
                orderRepository, userRepository);
    }

    @Test
    void getOrderList() {
        UserId userId = UserId.generate();

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

        given(orderRepository.findAllByUserIdOrderByIdDesc(userId))
                .willReturn(List.of(order));

        OrderListDto orderListDto = getOrderListService.getOrderList(userId);

        assertThat(orderListDto.orders()).hasSize(1);
    }

    @Test
    void getAdminOrderList() {
        User user = Fixtures.user("tester");
        Order order = Fixtures.order(user);

        given(orderRepository.findAllByOrderByIdDesc())
                .willReturn(List.of(order));
        given(userRepository.findAllByIdIn(List.of(user.id())))
                .willReturn(List.of(user));

        AdminOrderListDto ordersDto = getOrderListService.getAdminOrderList();

        assertThat(ordersDto.orders()).hasSize(1);
    }
}
