package kr.megaptera.makaobank.services;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.megaptera.makaobank.models.Account;
import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.repositories.AccountRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AccountServiceTest {
    AccountService accountService;
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);

        given(accountRepository.findByAccountNumber(any()))
                .willReturn(Optional.of(Account.fake("1234")));

        accountService = new AccountService(accountRepository);
    }

    @Test
    void account() {
        AccountNumber accountNumber = new AccountNumber("1234");

        Account account = accountService.detail(accountNumber);

        verify(accountRepository).findByAccountNumber(accountNumber);

        assertThat(account.accountNumber()).isEqualTo(accountNumber);
    }
}
