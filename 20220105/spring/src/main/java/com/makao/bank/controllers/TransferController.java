package com.makao.bank.controllers;

import com.makao.bank.models.Account;
import com.makao.bank.services.AccountService;
import com.makao.bank.services.TransferService;
import com.makao.bank.views.AccountNotFoundPageGenerator;
import com.makao.bank.views.PageGenerator;
import com.makao.bank.views.TransferPageGenerator;
import com.makao.bank.views.TransferSuccessPageGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {
    private final TransferService transferService;
    private final AccountService accountService;

    public TransferController(TransferService transferService,
                              AccountService accountService) {
        this.transferService = transferService;
        this.accountService = accountService;
    }

    @GetMapping("/transfer")
    public String transferPage() {
        PageGenerator pageGenerator = new TransferPageGenerator();
        return pageGenerator.html();
    }

    @PostMapping("/transfer")
    public String transfer(
            @RequestParam("to") String to,
            @RequestParam("amount") long amount
    ) {
        Account myAccount = accountService.myAccount();
        Account receiver = transferService.transfer(
                myAccount.identifier(), to, amount);

        PageGenerator pageGenerator = receiver == null
                ? new AccountNotFoundPageGenerator()
                : new TransferSuccessPageGenerator();

        return pageGenerator.html();
    }
}
