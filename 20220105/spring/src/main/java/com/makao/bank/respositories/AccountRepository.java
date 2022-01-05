package com.makao.bank.respositories;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.makao.bank.models.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    private Map<String, Account> accounts = new HashMap<>();

    public AccountRepository() {
        reset();
    }

    public Account find(String identifier) {
        if (!accounts.containsKey(identifier)) {
            return null;
        }

        Account account = accounts.get(identifier);
        return account;
    }

    public void reset() {
        Stream.of(
                new Account("1234", "Ashal", 3000),
                new Account("2345", "JOKER", 1000)
        ).forEach(account -> {
            accounts.put(account.identifier(), account);
        });
    }
}
