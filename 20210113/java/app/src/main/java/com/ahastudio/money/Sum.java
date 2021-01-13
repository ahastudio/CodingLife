package com.ahastudio.money;

public class Sum implements Expression {
    private Expression left;
    private Expression right;

    public Sum(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Money reduce(String currency, Bank bank) {
        return left.reduce(currency, bank).plus(right.reduce(currency, bank));
    }

    public Sum sum(Money other) {
        return new Sum(this, other);
    }
}
