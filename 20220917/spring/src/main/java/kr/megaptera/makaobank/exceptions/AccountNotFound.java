package kr.megaptera.makaobank.exceptions;

public class AccountNotFound extends RuntimeException {
    public AccountNotFound(String accountNumber) {
        super("Account not found (account number: " + accountNumber + ")");
    }
}
