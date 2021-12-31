package repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import models.Account;

public class AccountRepository {
    private final Map<String, Account> accounts = new HashMap<>();

    public AccountRepository() {
        Stream.of(
                new Account("1234", "Ashal", 3000),
                new Account("2345", "JOKER", 1000)
        ).forEach(account -> {
            accounts.put(account.identifier(), account);
        });
    }

    public Account find(String identifier) {
        return accounts.get(identifier);
    }

    public Account find(String identifier, String nextIdentifier) {
        Account account = find(identifier);
        if (account == null) {
            account = find(nextIdentifier);
        }
        return account;
    }
}
