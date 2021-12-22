package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionResultTest {
    @Test
    void creation() {
        Transaction transaction = new Transaction("입금", 100);
        int amount = 1000 + 100;

        TransactionResult transactionResult =
                new TransactionResult(transaction, amount);

        assertArrayEquals(new String[]{"입금", "100", "1100"},
                transactionResult.values());
    }

    @Test
    void csvRow() {
        Transaction transaction = new Transaction("입금", 100);
        int amount = 1000 + 100;

        TransactionResult transactionResult =
                new TransactionResult(transaction, amount);

        assertEquals("입금,100,1100", transactionResult.toCsvRow());
    }
}
