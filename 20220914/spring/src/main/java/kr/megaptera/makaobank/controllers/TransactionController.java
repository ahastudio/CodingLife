package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.TransferDto;
import kr.megaptera.makaobank.dtos.TransferResultDto;
import kr.megaptera.makaobank.exceptions.AccountNotFound;
import kr.megaptera.makaobank.exceptions.IncorrectAmount;
import kr.megaptera.makaobank.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransferService transferService;

    public TransactionController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransferResultDto transfer(
            @Valid @RequestBody TransferDto transferDto
    ) {
        // TODO: 인증 후 제대로 처리할 것!
        String accountNumber = "1234";

        Long amount = transferService.transfer(
                accountNumber,
                transferDto.getTo(),
                transferDto.getAmount());

        return new TransferResultDto(amount);
    }

    @ExceptionHandler(AccountNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String accountNotFound() {
        // TODO: 에러 DTO 사용할 것!
        return "";
    }

    @ExceptionHandler(IncorrectAmount.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String incorrectAmount() {
        // TODO: 에러 DTO 사용할 것!
        return "";
    }
}
