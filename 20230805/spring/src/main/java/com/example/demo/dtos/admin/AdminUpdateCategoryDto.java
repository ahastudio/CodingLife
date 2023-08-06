package com.example.demo.dtos.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminUpdateCategoryDto(
        @NotBlank
        String name,

        @NotNull
        Boolean hidden
) {
}
