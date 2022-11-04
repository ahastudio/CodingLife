package kr.megaptera.makaobank.repositories;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import kr.megaptera.makaobank.models.Account;
import kr.megaptera.makaobank.models.AccountNumber;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void save() {
        Account account = new Account(new AccountNumber("12345"), "Tester");

        accountRepository.save(account);
    }
}
