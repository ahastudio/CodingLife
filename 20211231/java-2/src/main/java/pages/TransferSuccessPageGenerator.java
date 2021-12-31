package pages;

import models.Account;

public class TransferSuccessPageGenerator extends PageGenerator {
    private Account account;

    public TransferSuccessPageGenerator(Account account) {
        super();

        this.account = account;
    }

    @Override
    public String content() {
        return "계좌 이체 성공!";
    }
}
