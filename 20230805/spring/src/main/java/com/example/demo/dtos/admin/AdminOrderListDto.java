package com.example.demo.dtos.admin;

import java.util.List;

public record AdminOrderListDto(
        List<AdminOrderSummaryDto> orders
) {
}
