package kr.megaptera.makaobank.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.megaptera.makaobank.exceptions.AccountNotFound;
import kr.megaptera.makaobank.models.Account;
import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.models.Transaction;
import kr.megaptera.makaobank.repositories.AccountRepository;
import kr.megaptera.makaobank.repositories.TransactionRepository;

@Service
@Transactional
public class TransferService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransferService(AccountRepository accountRepository,
                           TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Long transfer(AccountNumber from, AccountNumber to,
                         Long amount, String name) {
        Account account1 = accountRepository.findByAccountNumber(from)
                .orElseThrow(() -> new AccountNotFound(from));
        Account account2 = accountRepository.findByAccountNumber(to)
                .orElseThrow(() -> new AccountNotFound(to));

        account1.transferTo(account2, amount);

        Transaction transaction = new Transaction(
                account1.accountNumber(), account2.accountNumber(),
                amount, name
        );
        transactionRepository.save(transaction);

        return amount;
    }
}
