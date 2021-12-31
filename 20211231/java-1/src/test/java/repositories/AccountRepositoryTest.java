package repositories;

import models.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AccountRepositoryTest {
    @Test
    void find() {
        AccountRepository accountRepository = new AccountRepository();

        Account account = accountRepository.find("1234");

        assertEquals("1234", account.identifier());
        assertEquals("Ashal", account.name());
        assertEquals(3000, account.amount());
    }

    @Test
    void notFound() {
        AccountRepository accountRepository = new AccountRepository();

        Account account = accountRepository.find("4321");

        assertNull(account);
    }
}
