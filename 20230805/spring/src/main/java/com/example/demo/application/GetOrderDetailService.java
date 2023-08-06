package com.example.demo.application;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.ImageDto;
import com.example.demo.dtos.OrderDetailDto;
import com.example.demo.dtos.OrderLineItemDto;
import com.example.demo.models.Order;
import com.example.demo.models.OrderId;
import com.example.demo.models.OrderLineItem;
import com.example.demo.models.OrderOption;
import com.example.demo.models.UserId;
import com.example.demo.repositories.OrderRepository;

@Service
@Transactional(readOnly = true)
public class GetOrderDetailService {
    private final static DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final OrderRepository orderRepository;

    public GetOrderDetailService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDetailDto getOrderDetail(OrderId orderId, UserId userId) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow();

        return new OrderDetailDto(
                order.id().toString(),
                order.title(),
                mapToLineItemDtos(order),
                order.totalPrice().asLong(),
                order.status().toString(),
                order.orderedAt().format(DATE_TIME_FORMATTER)
        );
    }

    private List<OrderLineItemDto> mapToLineItemDtos(Order order) {
        return IntStream.range(0, order.lineItemSize())
                .mapToObj(order::lineItem)
                .map(this::mapToLineItemDto)
                .toList();
    }

    private OrderLineItemDto mapToLineItemDto(OrderLineItem lineItem) {
        return new OrderLineItemDto(
                lineItem.id().toString(),
                mapToProductDto(lineItem),
                mapToOptionDtos(lineItem),
                lineItem.unitPrice().asLong(),
                lineItem.quantity(),
                lineItem.totalPrice().asLong()
        );
    }

    private OrderLineItemDto.ProductDto mapToProductDto(
            OrderLineItem lineItem) {
        return new OrderLineItemDto.ProductDto(
                lineItem.productId().toString(),
                new ImageDto(""),
                lineItem.productName()
        );
    }

    private List<OrderLineItemDto.OptionDto> mapToOptionDtos(
            OrderLineItem lineItem) {
        return IntStream.range(0, lineItem.optionSize())
                .mapToObj(lineItem::option)
                .map(this::mapToOptionDto)
                .toList();
    }

    private OrderLineItemDto.OptionDto mapToOptionDto(OrderOption orderOption) {
        String optionName = orderOption.name();
        String optionItemName = orderOption.optionItem().name();

        return new OrderLineItemDto.OptionDto(
                optionName,
                new OrderLineItemDto.OptionItemDto(optionItemName));
    }
}
