package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.LoginRequestDto;
import kr.megaptera.makaobank.dtos.LoginResultDto;
import kr.megaptera.makaobank.exceptions.LoginFailed;
import kr.megaptera.makaobank.models.Account;
import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("session")
public class SessionController {
    private final LoginService loginService;

    public SessionController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
            @RequestBody LoginRequestDto loginReuqestDto
    ) {
        AccountNumber accountNumber =
                new AccountNumber(loginReuqestDto.getAccountNumber());
        String password = loginReuqestDto.getPassword();

        Account account = loginService.login(accountNumber, password);

        return new LoginResultDto(
                account.accountNumber().value(),
                account.name(),
                account.amount()
        );
    }

    @ExceptionHandler(LoginFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Login failed";
    }
}
