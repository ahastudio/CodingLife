package com.example.demo.models;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @Test
    void plus() {
        Money two = new Money(2_000L);
        Money three = new Money(3_000L);
        Money five = new Money(5_000L);

        assertThat(two.plus(three)).isEqualTo(five);
    }

    @Test
    void times() {
        Money three = new Money(3_000L);
        Money six = new Money(6_000L);

        assertThat(three.times(2)).isEqualTo(six);
    }

    @Test
    void sum() {
        Money sum = Stream.of(
                new Money(128_000L),
                new Money(256_000L),
                new Money(236_000L)
        ).reduce(Money.ZERO, Money::plus);

        assertThat(sum).isEqualTo(new Money(620_000L));
    }
}
