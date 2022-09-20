package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.LoginRequestDto;
import kr.megaptera.makaobank.dtos.LoginResultDto;
import kr.megaptera.makaobank.exceptions.LoginFailed;
import kr.megaptera.makaobank.models.Account;
import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.services.LoginService;
import kr.megaptera.makaobank.utils.JwtUtil;
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
    private final JwtUtil jwtUtil;

    public SessionController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
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

        String accessToken = jwtUtil.encode(accountNumber);

        return new LoginResultDto(
                accessToken, account.name(), account.amount());
    }

    @ExceptionHandler(LoginFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Login failed";
    }
}
