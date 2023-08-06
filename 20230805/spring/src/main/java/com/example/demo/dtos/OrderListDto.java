package com.example.demo.dtos;

import java.util.List;

public record OrderListDto(
        List<OrderSummaryDto> orders
) {
}
