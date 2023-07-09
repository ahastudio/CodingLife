package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @Test
    void plus() {
        Money two = new Money(2L);
        Money three = new Money(3L);
        Money five = new Money(5L);

        assertThat(two.plus(three)).isEqualTo(five);
    }

    @Test
    void times() {
        Money two = new Money(2L);
        Money six = new Money(6L);

        assertThat(two.times(3)).isEqualTo(six);
    }
}
