package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void creation() {
        Account account = new Account("1234", "Ashal", 3000);

        assertEquals("1234", account.identifier());
        assertEquals("Ashal", account.name());
        assertEquals(3000, account.amount());
    }

    @Test
    void transfer() {
        Account sender = new Account("1234", "Ashal", 3000);
        Account receiver = new Account("2345", "JOKER", 500);

        sender.transfer(receiver, 1000);

        assertEquals(2000, sender.amount());
        assertEquals(1500, receiver.amount());
    }

    // TODO: 거래 내역
}