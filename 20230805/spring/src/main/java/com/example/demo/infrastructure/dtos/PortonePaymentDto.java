package com.example.demo.infrastructure.dtos;

public record PortonePaymentDto(
        Response response
) {
    public record Response(
            Long amount
    ) {
    }
}
