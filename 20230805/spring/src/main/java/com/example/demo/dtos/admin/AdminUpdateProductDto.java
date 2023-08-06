package com.example.demo.dtos.admin;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AdminUpdateProductDto(
        @NotBlank
        String categoryId,

        List<ImageDto> images,

        @NotBlank
        String name,

        @Min(0)
        Long price,

        List<OptionDto> options,

        @NotBlank
        String description,

        boolean hidden
) {
    public record ImageDto(
            String id,

            @NotBlank
            String url
    ) {
    }

    public record OptionDto(
            String id,

            @NotBlank
            String name,

            @NotEmpty
            List<OptionItemDto> items
    ) {
    }

    public record OptionItemDto(
            String id,

            @NotBlank
            String name
    ) {
    }
}
