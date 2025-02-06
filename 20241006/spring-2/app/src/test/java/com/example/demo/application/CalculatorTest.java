package com.example.demo.application;

import com.example.demo.infrastructure.Calculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CalculatorTest {
    private CalculationRepository calculationRepository;
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculationRepository = mock(CalculationRepository.class);

        calculator = new Calculator(calculationRepository);
    }

    @Test
    void plus() {
        Calculation calculation = calculator.calculate(10, 2, "+");

        assertThat(calculation.getNumber1()).isEqualTo(10);
        assertThat(calculation.getNumber2()).isEqualTo(2);
        assertThat(calculation.getOperator()).isEqualTo("+");
        assertThat(calculation.getResult()).isEqualTo(12);

        verify(calculationRepository).add(any());
    }

    @Test
    void minus() {
        Calculation calculation = calculator.calculate(10, 2, "-");

        assertThat(calculation.getNumber1()).isEqualTo(10);
        assertThat(calculation.getNumber2()).isEqualTo(2);
        assertThat(calculation.getOperator()).isEqualTo("-");
        assertThat(calculation.getResult()).isEqualTo(8);

        verify(calculationRepository).add(any());
    }

    @Test
    void multiply() {
        Calculation calculation = calculator.calculate(10, 2, "*");

        assertThat(calculation.getNumber1()).isEqualTo(10);
        assertThat(calculation.getNumber2()).isEqualTo(2);
        assertThat(calculation.getOperator()).isEqualTo("*");
        assertThat(calculation.getResult()).isEqualTo(20);

        verify(calculationRepository).add(any());
    }

    @Test
    void divide() {
        Calculation calculation = calculator.calculate(10, 2, "/");

        assertThat(calculation.getNumber1()).isEqualTo(10);
        assertThat(calculation.getNumber2()).isEqualTo(2);
        assertThat(calculation.getOperator()).isEqualTo("/");
        assertThat(calculation.getResult()).isEqualTo(5);

        verify(calculationRepository).add(any());
    }

    @Test
    void divideByZero() {
        assertThatThrownBy(() -> {
            calculator.calculate(10, 0, "/");
        });

        verify(calculationRepository, never()).add(any());
    }

    @Test
    void getCalculationList() {
        List<Calculation> calculations = List.of(
                new Calculation("+", 1, 2, 3)
        );
        when(calculationRepository.getAll()).thenReturn(calculations);

        assertThat(calculator.getCalculationList()).hasSize(1);
    }
}
