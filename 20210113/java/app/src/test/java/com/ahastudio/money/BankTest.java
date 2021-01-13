package com.ahastudio.money;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankTest {
    @Test
    void rate() {
        Bank bank = new Bank();
        bank.setRate("USD", "CHF", 2.0);
        bank.setRate("CHF", "USD", 0.5);

        assertThat(bank.rate("USD", "USD")).isEqualTo(1.0);
        assertThat(bank.rate("CHF", "CHF")).isEqualTo(1.0);

        assertThat(bank.rate("USD", "CHF")).isEqualTo(2.0);
        assertThat(bank.rate("CHF", "USD")).isEqualTo(0.5);
    }
}