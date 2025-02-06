package com.example.demo.application;

public class OperatorMultiply implements Operator {
    @Override
    public int calculate(int number1, int number2) {
        return number1 * number2;
    }
}
