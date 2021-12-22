package models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int amount = 0;

    public List<TransactionResult> process(List<Transaction> transactions) {
        List<TransactionResult> transactionResults = new ArrayList<>();

        for (Transaction transaction : transactions) {
            amount = transaction.process(amount);
            TransactionResult transactionResult =
                    new TransactionResult(transaction, amount);
            transactionResults.add(transactionResult);
        }

        return transactionResults;
    }
}
