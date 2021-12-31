package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    @Test
    void creation() {
        Account sender = new Account("1234", "Ashal", 3000);
        Account receiver = new Account("2345", "JOKER", 1000);
        long amount = 800;

        Transaction transaction = new Transaction(sender, receiver, amount);

        assertEquals(sender, transaction.sender());
        assertEquals(receiver, transaction.receiver());
        assertEquals(amount, transaction.amount());
    }

    @Test
    void command() {
        Account sender = new Account("1234", "Ashal", 3000);
        Account receiver = new Account("2345", "JOKER", 1000);
        long amount = 800;

        Transaction transaction = new Transaction(sender, receiver, amount);

        assertEquals("송금", transaction.command(sender));
        assertEquals("입금", transaction.command(receiver));
    }

    @Test
    void other() {
        Account sender = new Account("1234", "Ashal", 3000);
        Account receiver = new Account("2345", "JOKER", 1000);
        long amount = 800;

        Transaction transaction = new Transaction(sender, receiver, amount);

        assertEquals(receiver, transaction.other(sender));
        assertEquals(sender, transaction.other(receiver));
    }
}
