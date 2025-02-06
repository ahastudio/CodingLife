package com.example.demo.presentation.dto;

public class CalculationResponseDto {
    private final int number1;
    private final int number2;
    private final String operator;
    private final int result;

    public CalculationResponseDto(
            int number1, int number2, String operator, int result) {
        this.number1 = number1;
        this.number2 = number2;
        this.operator = operator;
        this.result = result;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public String getOperator() {
        return operator;
    }

    public int getResult() {
        return result;
    }
}
