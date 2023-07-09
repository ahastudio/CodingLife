package com.example.demo.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Money {
    public static final Money ZERO = new Money(0L);

    @Column(name = "amount")
    private Long amount;

    private Money() {
    }

    public Money(Long amount) {
        this.amount = amount;
    }

    public Long asLong() {
        return amount;
    }

    public Money plus(Money other) {
        return new Money(amount + other.amount);
    }

    public Money times(int quantity) {
        return new Money(amount * quantity);
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money other = (Money) o;
        return Objects.equals(amount, other.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
