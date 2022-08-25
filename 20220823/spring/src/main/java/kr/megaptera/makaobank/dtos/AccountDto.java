package kr.megaptera.makaobank.dtos;

public class AccountDto {
    private final String accountNumber;

    private final String name;

    private final Long amount;

    public AccountDto(String accountNumber, String name, Long amount) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
