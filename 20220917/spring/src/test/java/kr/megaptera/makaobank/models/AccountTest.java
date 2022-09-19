package kr.megaptera.makaobank.models;

import kr.megaptera.makaobank.exceptions.IncorrectAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    final Long AMOUNT1 = 1_000_000L;
    final Long AMOUNT2 = 20L;

    Account account1;
    Account account2;

    @BeforeEach
    void setUp() {
        account1 = new Account(1L, "1234", "FROM", AMOUNT1);
        account2 = new Account(2L, "5678", "TO", AMOUNT2);
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

        Account account1 = new Account(1L, "1234", "FROM", AMOUNT1);
        Account account2 = new Account(2L, "5678", "TO", AMOUNT2);

        assertThrows(IncorrectAmount.class, () -> {
            account1.transferTo(account2, transferAmount);
        });
    }

    @Test
    void transferWithTooLargeAmount() {
        final Long transferAmount = AMOUNT1 + 100_000L;

        Account account1 = new Account(1L, "1234", "FROM", AMOUNT1);
        Account account2 = new Account(2L, "5678", "TO", AMOUNT2);

        assertThrows(IncorrectAmount.class, () -> {
            account1.transferTo(account2, transferAmount);
        });
    }
}
