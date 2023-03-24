package com.example.demo.controllers.dtos;

public record AddCartLineItemDto(
        String productId,
        int quantity
) {
}
