package com.makao.bank.views;

import com.makao.bank.models.Account;

public class AccountPageGenerator extends PageGenerator {
    private final Account account;

    public AccountPageGenerator(Account account) {
        super();
        this.account = account;
    }

    @Override
    public String content() {
        return "<p>계좌 번호: " + account.identifier() + "</p>" +
                "<p>잔액: " + account.amount() + "원</p>";
    }
}
