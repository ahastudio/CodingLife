package com.makao.bank.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void creation() {
        Account account = new Account("1234", "Ashal", 3000);

        assertThat(account.identifier()).isEqualTo("1234");
        assertThat(account.name()).isEqualTo("Ashal");
        assertThat(account.amount()).isEqualTo(3000);
    }

    @Test
    void transfer() {
        long amount = 500;

        Account sender = new Account("1234", "Ashal", 3000);
        Account receiver = new Account("2345", "JOKER", 1000);

        sender.transfer(receiver, amount);

        assertThat(sender.amount()).isEqualTo(3000 - amount);
        assertThat(receiver.amount()).isEqualTo(1000 + amount);
    }

    @Test
    void invalidTransfer() {
        long amount = -500;

        Account sender = new Account("1234", "Ashal", 3000);
        Account receiver = new Account("2345", "JOKER", 1000);

        sender.transfer(receiver, amount);

        assertThat(sender.amount()).isEqualTo(3000);
        assertThat(receiver.amount()).isEqualTo(1000);
    }
}
