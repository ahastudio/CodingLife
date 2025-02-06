package com.example.demo.presentation.dto;

import com.example.demo.infrastructure.Calculation;

import java.util.List;
import java.util.stream.Collectors;

public class CalculationListResponseDto {
    private final List<CalculationResponseDto> calculations;

    public CalculationListResponseDto(List<CalculationResponseDto> calculations) {
        this.calculations = calculations;
    }

    public static CalculationListResponseDto of(List<Calculation> calculations) {
        return new CalculationListResponseDto(
                calculations.stream()
                        .map(calculation ->
                                new CalculationResponseDto(
                                        calculation.getNumber1(),
                                        calculation.getNumber2(),
                                        calculation.getOperator(),
                                        calculation.getResult()
                                )
                        )
                        .collect(Collectors.toList())
        );
    }

    public List<CalculationResponseDto> getCalculations() {
        return calculations;
    }
}
