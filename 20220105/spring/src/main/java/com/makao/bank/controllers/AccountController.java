package com.makao.bank.controllers;

import com.makao.bank.models.Account;
import com.makao.bank.services.AccountService;
import com.makao.bank.views.AccountPageGenerator;
import com.makao.bank.views.PageGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account")
    public String account() {
        Account account = accountService.myAccount();

        PageGenerator pageGenerator = new AccountPageGenerator(account);

        return pageGenerator.html();
    }
}
