package com.makao.bank.views;

import com.makao.bank.models.Account;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountPageGeneratorTest {
    @Test
    void content() {
        Account account = new Account("1234", "Ashal", 3000);

        PageGenerator pageGenerator = new AccountPageGenerator(account);

        String html = pageGenerator.content();

        assertThat(html).contains("잔액: 3000원");
    }
}
