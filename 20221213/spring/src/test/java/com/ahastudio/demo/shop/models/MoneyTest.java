package com.ahastudio.demo.shop.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @Test
    void equals() {
        assertThat(Money.krw(5_000L)).isEqualTo(Money.krw(5_000L));

        assertThat(Money.krw(5_000L)).isNotEqualTo(Money.krw(10_000L));
    }
}
