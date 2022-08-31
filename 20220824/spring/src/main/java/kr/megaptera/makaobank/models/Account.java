package kr.megaptera.makaobank.models;

import kr.megaptera.makaobank.dtos.AccountDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String accountNumber;

    private String name;

    private Long amount;

    public Account() {
    }

    public Account(Long id, String accountNumber, String name, Long amount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.name = name;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public static Account fake(String accountNumber) {
        return new Account(1L, accountNumber, "Tester", 100L);
    }

    public AccountDto toDto() {
        return new AccountDto(accountNumber, name, amount);
    }
}
