package com.example.demo.controllers.dtos;

public record CartLineItemAdditionDto(
        Long productId,
        int quantity
) {
}
