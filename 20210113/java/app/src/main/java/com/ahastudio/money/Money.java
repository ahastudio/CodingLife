package com.ahastudio.money;

public class Money implements Expression {
    protected double amount;
    protected String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(double amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(double amount) {
        return new Money(amount, "CHF");
    }

    public Money times(double factor) {
        return new Money(amount * factor, currency);
    }

    public Money plus(Money other) {
        return new Money(amount + other.amount, currency);
    }

    @Override
    public String toString() {
        return currency + "(" + amount + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Money)) {
            return false;
        }
        Money money = (Money) other;
        return amount == money.amount;
    }

    public Sum sum(Money other) {
        return new Sum(this, other);
    }

    public Money reduce(String currency, Bank bank) {
        double rate = bank.rate(this.currency, currency);
        return new Money(amount * rate, currency);
    }
}
