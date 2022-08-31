package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.AccountNotFound;
import kr.megaptera.makaobank.models.Account;
import kr.megaptera.makaobank.respositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account detail(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFound(accountNumber));
    }
}
