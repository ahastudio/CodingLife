package com.ahastudio.money;

public interface Expression {
    Money reduce(String currency, Bank bank);
}
