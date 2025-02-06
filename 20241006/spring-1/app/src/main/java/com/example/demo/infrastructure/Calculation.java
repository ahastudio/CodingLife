package com.example.demo.infrastructure;

public class Calculation {
    private String operator;
    private int number1;
    private int number2;
    private int result;

    public Calculation(String operator, int number1, int number2, int result) {
        this.operator = operator;
        this.number1 = number1;
        this.number2 = number2;
        this.result = result;
    }

    public String getOperator() {
        return operator;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public int getResult() {
        return result;
    }
}
