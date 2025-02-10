package com.example.demo.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Money(
        @Column(name = "amount")
        BigDecimal amount,

        @Column(name = "currency")
        String currency
) {
}
