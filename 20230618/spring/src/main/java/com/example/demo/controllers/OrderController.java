package com.example.demo.controllers;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.CreateOrderService;
import com.example.demo.application.GetOrderDetailService;
import com.example.demo.application.GetOrderListService;
import com.example.demo.dtos.OrderDetailDto;
import com.example.demo.dtos.OrderListDto;
import com.example.demo.dtos.OrderRequestDto;
import com.example.demo.models.Address;
import com.example.demo.models.OrderId;
import com.example.demo.models.Payment;
import com.example.demo.models.PhoneNumber;
import com.example.demo.models.PostalCode;
import com.example.demo.models.Receiver;
import com.example.demo.models.UserId;
import com.example.demo.security.AuthUser;
import com.example.demo.security.UserRequired;

@UserRequired
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final CreateOrderService createOrderService;
    private final GetOrderListService getOrderListService;
    private final GetOrderDetailService getOrderDetailService;

    public OrderController(CreateOrderService createOrderService,
                           GetOrderListService getOrderListService,
                           GetOrderDetailService getOrderDetailService) {
        this.createOrderService = createOrderService;
        this.getOrderListService = getOrderListService;
        this.getOrderDetailService = getOrderDetailService;
    }

    @GetMapping
    public OrderListDto list(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        UserId userId = new UserId(authUser.id());

        return getOrderListService.getOrderList(userId);
    }

    @GetMapping("/{id}")
    public OrderDetailDto detail(Authentication authentication,
                                 @PathVariable String id) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        OrderId orderId = new OrderId(id);
        UserId userId = new UserId(authUser.id());

        return getOrderDetailService.getOrderDetail(orderId, userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            Authentication authentication,
            @Valid @RequestBody OrderRequestDto orderRequestDto
    ) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        UserId userId = new UserId(authUser.id());

        OrderRequestDto.ReceiverDto receiverDto = orderRequestDto.receiver();
        OrderRequestDto.PaymentDto paymentDto = orderRequestDto.payment();

        Receiver receiver = new Receiver(
                receiverDto.name(),
                new Address(
                        receiverDto.address1(),
                        receiverDto.address2(),
                        new PostalCode(receiverDto.postalCode())
                ),
                new PhoneNumber(receiverDto.phoneNumber())
        );

        Payment payment = new Payment(
                paymentDto.merchantId(),
                paymentDto.transactionId()
        );

        createOrderService.createOrder(userId, receiver, payment);

        return "Created";
    }
}
