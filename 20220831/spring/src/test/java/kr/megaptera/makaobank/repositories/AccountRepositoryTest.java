package kr.megaptera.makaobank.repositories;

import kr.megaptera.makaobank.models.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void creation() {
        Account account = new Account("12345", "Tester");

        accountRepository.save(account);
    }
}
