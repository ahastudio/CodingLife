package kr.megaptera.makaobank.exceptions;

import kr.megaptera.makaobank.models.AccountNumber;

public class AccountNotFound extends RuntimeException {
    public AccountNotFound(AccountNumber accountNumber) {
        super("Account not found: " + accountNumber);
    }
}
