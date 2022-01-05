package com.makao.bank.services;

import com.makao.bank.models.Account;
import com.makao.bank.respositories.AccountRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TransferServiceTest {
    @Test
    void transfer() {
        AccountRepository accountRepository = new AccountRepository();

        TransferService transferService = new TransferService(accountRepository);

        Account receiver = transferService.transfer("1234", "2345", 200);

        assertThat(receiver.amount()).isEqualTo(1000 + 200);
    }

    @Test
    void transferWithNotExistedReceiver() {
        AccountRepository accountRepository = new AccountRepository();

        TransferService transferService = new TransferService(accountRepository);

        Account receiver = transferService.transfer("1234", "-1", 200);

        assertThat(receiver).isNull();
    }
}
