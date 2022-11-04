package kr.megaptera.makaobank.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.megaptera.makaobank.dtos.AccountNotFoundErrorDto;
import kr.megaptera.makaobank.dtos.ErrorDto;
import kr.megaptera.makaobank.dtos.IncorrectAmountErrorDto;
import kr.megaptera.makaobank.dtos.TransactionDto;
import kr.megaptera.makaobank.dtos.TransactionsDto;
import kr.megaptera.makaobank.dtos.TransferDto;
import kr.megaptera.makaobank.dtos.TransferResultDto;
import kr.megaptera.makaobank.exceptions.AccountNotFound;
import kr.megaptera.makaobank.exceptions.IncorrectAmount;
import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.services.TransactionService;
import kr.megaptera.makaobank.services.TransferService;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;

    private final TransferService transferService;

    public TransactionController(TransactionService transactionService,
                                 TransferService transferService) {
        this.transactionService = transactionService;
        this.transferService = transferService;
    }

    @GetMapping
    public TransactionsDto list(
            @RequestAttribute("accountNumber") AccountNumber accountNumber,
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        List<TransactionDto> transactionDtos =
                transactionService.list(accountNumber, page)
                        .stream()
                        .map(transaction -> transaction.toDto(accountNumber))
                        .collect(Collectors.toList());

        return new TransactionsDto(transactionDtos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransferResultDto transfer(
            @RequestAttribute("accountNumber") AccountNumber sender,
            @Valid @RequestBody TransferDto transferDto
    ) {
        AccountNumber receiver = new AccountNumber(transferDto.getTo());

        Long amount = transferService.transfer(
                sender, receiver,
                transferDto.getAmount(), transferDto.getName());

        return new TransferResultDto(amount);
    }

    @ExceptionHandler(AccountNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto accountNotFound() {
        return new AccountNotFoundErrorDto();
    }

    @ExceptionHandler(IncorrectAmount.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto incorrectAmount() {
        return new IncorrectAmountErrorDto();
    }
}
