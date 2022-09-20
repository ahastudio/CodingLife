package kr.megaptera.makaobank.dtos;

import java.util.List;

public class TransactionsDto {
    private final List<TransactionDto> transactions;

    public TransactionsDto(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }
}
