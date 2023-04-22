package com.ahastudio.demo.shop.controllers.dtos;

public record ProductDetailDto(
        String id,
        String name,
        Long price
) {
}
