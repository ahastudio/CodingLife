package com.example.demo.controllers.dtos;

import java.util.List;

public record CartDto(
        List<LineItemDto> lineItems
) {
    public record LineItemDto(
            Long productId,
            int quantity
    ) {
    }
}
