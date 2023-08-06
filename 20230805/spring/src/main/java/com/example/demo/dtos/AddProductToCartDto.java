package com.example.demo.dtos;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AddProductToCartDto(
        @NotBlank
        String productId,

        List<Option> options,

        @Min(1)
        int quantity
) {
    public record Option(
            @NotBlank
            String id,

            @NotBlank
            String itemId
    ) {
    }
}
