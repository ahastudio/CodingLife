import models.Transaction;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MakaoBankTest {
    @Test
    void loadTransactions() throws FileNotFoundException {
        MakaoBank makaoBank = new MakaoBank();

        List<Transaction> transactions = makaoBank.loadTransactions();

        assertNotNull(transactions);

        assertEquals(5, transactions.size());

        Transaction transaction1 = transactions.get(0);
        Transaction transaction2 = transactions.get(1);

        assertEquals(new Transaction("잔액", 1000), transaction1);
        assertEquals(new Transaction("입금", 2000), transaction2);
    }
}