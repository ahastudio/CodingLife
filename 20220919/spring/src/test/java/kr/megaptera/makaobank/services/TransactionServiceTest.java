package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.models.Transaction;
import kr.megaptera.makaobank.respositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TransactionServiceTest {
    TransactionRepository transactionRepository;
    TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);

        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    void list() {
        AccountNumber accountNumber = new AccountNumber("1234");

        Transaction transaction = mock(Transaction.class);

        given(transactionRepository
                .findAllBySenderOrReceiver(
                        eq(accountNumber), eq(accountNumber), any()))
                .willReturn(List.of(transaction));

        List<Transaction> transactions =
                transactionService.list(accountNumber, 1);

        assertThat(transactions).hasSize(1);
    }
}
