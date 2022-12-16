package com.example.demo.models;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Money {
    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Long asLong() {
        return this.amount.longValue();
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    public static Money krw(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }
}
