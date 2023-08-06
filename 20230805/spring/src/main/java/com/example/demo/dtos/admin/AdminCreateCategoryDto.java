package com.example.demo.dtos.admin;

import jakarta.validation.constraints.NotBlank;

public record AdminCreateCategoryDto(
        @NotBlank
        String name
) {
}
