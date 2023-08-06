package com.example.demo.dtos.admin;

import jakarta.validation.constraints.NotBlank;

public record AdminUpdateOrderDto(
        @NotBlank
        String status
) {
}
