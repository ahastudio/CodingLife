package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import com.example.demo.presentation.dto.CalculationRequestDto;
import com.example.demo.presentation.dto.CalculationResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CalculationCreateHandler extends ResourceMethodHandler {
    private final Calculator calculator;

    private final ObjectMapper objectMapper;

    public CalculationCreateHandler(
            Calculator calculator,
            ObjectMapper objectMapper
    ) {
        this.calculator = calculator;
        this.objectMapper = objectMapper;
    }

    @Override
    public String key() {
        return "POST /calculations";
    }

    @Override
    public String handle(String content) throws JsonProcessingException {
        CalculationRequestDto requestDto =
                objectMapper.readValue(content, CalculationRequestDto.class);

        Calculation calculation = calculator.calculate(
                requestDto.getNumber1(),
                requestDto.getNumber2(),
                requestDto.getOperator());

        return objectMapper.writeValueAsString(
                new CalculationResponseDto(
                        calculation.getNumber1(),
                        calculation.getNumber2(),
                        calculation.getOperator(),
                        calculation.getResult()
                ));
    }
}
