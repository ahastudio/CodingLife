package kr.megaptera.makaobank.dtos;

public class AccountDto {
    private String accountNumber;
    private String name;
    private Long amount;

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
