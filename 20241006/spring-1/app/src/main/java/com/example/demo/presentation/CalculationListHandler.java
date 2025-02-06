package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import com.example.demo.presentation.dto.CalculationListResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculationListHandler extends ResourceMethodHandler {
    private final Calculator calculator;

    private final ObjectMapper objectMapper;

    public CalculationListHandler(
            Calculator calculator,
            ObjectMapper objectMapper
    ) {
        this.calculator = calculator;
        this.objectMapper = objectMapper;
    }

    @Override
    public String key() {
        return "GET /calculations";
    }

    @Override
    public String handle(String content) throws JsonProcessingException {
        List<Calculation> calculations = calculator.getCalculationList();

        return objectMapper.writeValueAsString(
                CalculationListResponseDto.of(calculations)
        );
    }
}
