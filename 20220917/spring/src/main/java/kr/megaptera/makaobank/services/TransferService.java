package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.AccountNotFound;
import kr.megaptera.makaobank.models.Account;
import kr.megaptera.makaobank.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransferService {
    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long transfer(String from, String to, Long amount) {
        Account account1 = accountRepository.findByAccountNumber(from)
                .orElseThrow(() -> new AccountNotFound(from));
        Account account2 = accountRepository.findByAccountNumber(to)
                .orElseThrow(() -> new AccountNotFound(to));

        account1.transferTo(account2, amount);

        return amount;
    }
}
