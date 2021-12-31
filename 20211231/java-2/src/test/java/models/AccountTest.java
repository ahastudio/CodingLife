package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        int oldSenderTransactionsCount = sender.transactionsCount();
        int oldReceiverTransactionsCount = receiver.transactionsCount();

        sender.transfer(receiver, 1000);

        assertEquals(2000, sender.amount());
        assertEquals(1500, receiver.amount());

        int newSenderTransactionsCount = sender.transactionsCount();
        int newReceiverTransactionsCount = receiver.transactionsCount();

        assertEquals(1, newSenderTransactionsCount - oldSenderTransactionsCount);
        assertEquals(1, newReceiverTransactionsCount - oldReceiverTransactionsCount);
    }

    @Test
    void transferMinus() {
        Account sender = new Account("1234", "Ashal", 3000);
        Account receiver = new Account("2345", "JOKER", 500);

        sender.transfer(receiver, -1000);

        assertEquals(3000, sender.amount());
        assertEquals(500, receiver.amount());
    }

    @Test
    void transactions() {
        Account sender = new Account("1234", "Ashal", 3000);
        Account receiver = new Account("2345", "JOKER", 500);

        sender.transfer(receiver, 1000);

        assertEquals(1, sender.transactions().size());
        assertEquals(1, receiver.transactions().size());
    }

    @Test
    void equals() {
        Account account1 = new Account("1234", "Ashal", 3000);
        Account account2 = new Account("2345", "JOKER", 500);

        assertTrue(account1.equals(account1));
        assertFalse(account1.equals(account2));
    }
}
