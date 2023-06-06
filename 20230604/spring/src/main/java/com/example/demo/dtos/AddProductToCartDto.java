package com.example.demo.dtos;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AddProductToCartDto(
        @NotBlank
        String productId,

        @NotEmpty
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
