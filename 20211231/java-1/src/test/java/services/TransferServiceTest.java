package services;

import models.Account;
import org.junit.jupiter.api.Test;
import repositories.AccountRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferServiceTest {
    @Test
    void transfer() {
        AccountRepository accountRepository = new AccountRepository();

        Account sender = accountRepository.find("1234");
        Account receiver = accountRepository.find("2345");

        TransferService transferService = new TransferService(accountRepository);

        long oldSenderAmount = sender.amount();
        long oldReceiverAmount = receiver.amount();

        transferService.transfer("1234", "2345", 1000);

        long newSenderAmount = sender.amount();
        long newReceiverAmount = receiver.amount();

        assertEquals(1000, oldSenderAmount - newSenderAmount);
        assertEquals(1000, newReceiverAmount - oldReceiverAmount);
    }
}
