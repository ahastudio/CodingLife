package com.example.demo.application;

public class OperatorDivide implements Operator {
    @Override
    public int calculate(int number1, int number2) {
        if (number2 == 0) {
            throw new IllegalArgumentException();
        }
        return number1 / number2;
    }
}
