package kr.megaptera.makaobank.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.megaptera.makaobank.exceptions.IncorrectAmount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {
    final Long AMOUNT1 = 1_000_000L;
    final Long AMOUNT2 = 20L;

    Account account1;
    Account account2;

    @BeforeEach
    void setUp() {
        account1 = new Account(1L, new AccountNumber("1234"), "FROM", AMOUNT1);
        account2 = new Account(2L, new AccountNumber("5678"), "TO", AMOUNT2);
    }

    @Test
    void transferTo() {
        final Long transferAmount = 100_000L;

        account1.transferTo(account2, transferAmount);

        assertThat(account1.amount()).isEqualTo(AMOUNT1 - transferAmount);
        assertThat(account2.amount()).isEqualTo(AMOUNT2 + transferAmount);
    }

    @Test
    void transferWithNegativeAmount() {
        final Long transferAmount = -100_000L;

        AccountNumber accountNumber1 = new AccountNumber("1234");
        AccountNumber accountNumber2 = new AccountNumber("5678");

        Account account1 = new Account(1L, accountNumber1, "FROM", AMOUNT1);
        Account account2 = new Account(2L, accountNumber2, "TO", AMOUNT2);

        assertThrows(IncorrectAmount.class, () -> {
            account1.transferTo(account2, transferAmount);
        });
    }

    @Test
    void transferWithTooLargeAmount() {
        final Long transferAmount = AMOUNT1 + 100_000L;

        AccountNumber accountNumber1 = new AccountNumber("1234");
        AccountNumber accountNumber2 = new AccountNumber("5678");

        Account account1 = new Account(1L, accountNumber1, "FROM", AMOUNT1);
        Account account2 = new Account(2L, accountNumber2, "TO", AMOUNT2);

        assertThrows(IncorrectAmount.class, () -> {
            account1.transferTo(account2, transferAmount);
        });
    }

    @Test
    void authenticate() {
        Account account = Account.fake("1234");
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        account.changePassword("password", passwordEncoder);

        assertThat(account.authenticate("password", passwordEncoder)).isTrue();
        assertThat(account.authenticate("xxx", passwordEncoder)).isFalse();
    }
}
