package com.makao.bank.services;

import com.makao.bank.models.Account;
import com.makao.bank.respositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account transfer(String from, String to, long amount) {
        Account receiver = accountRepository.find(to);
        if (receiver == null) {
            return null;
        }

        Account sender = accountRepository.find(from);
        sender.transfer(receiver, amount);
        return receiver;
    }
}
