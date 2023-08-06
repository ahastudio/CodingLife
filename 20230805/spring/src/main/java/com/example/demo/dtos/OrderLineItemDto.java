package com.example.demo.dtos;

import java.util.List;

public record OrderLineItemDto(
        String id,
        ProductDto product,
        List<OptionDto> options,
        long unitPrice,
        int quantity,
        long totalPrice
) {
    public record ProductDto(
            String id,
            ImageDto thumbnail,
            String name
    ) {
    }

    public record OptionDto(
            String name,
            OptionItemDto item
    ) {
    }

    public record OptionItemDto(
            String name
    ) {
    }
}
