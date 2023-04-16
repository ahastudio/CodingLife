package com.example.demo.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public record CreateProductDto(
        @NotBlank
        String name,

        @NotNull
        @Min(0)
        Long price,

        MultipartFile image
) {
}
