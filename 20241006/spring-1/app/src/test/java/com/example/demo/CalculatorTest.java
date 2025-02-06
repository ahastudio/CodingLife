package com.example.demo;

import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import com.example.demo.infrastructure.FakeCalculationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {
    FakeCalculationRepository calculationRepository;
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculationRepository = new FakeCalculationRepository();

        calculator = new Calculator(calculationRepository);
    }

    @Test
    void plus() {
        Calculation calculation = calculator.calculate(10, 2, "+");

        assertThat(calculation.getNumber1()).isEqualTo(10);
        assertThat(calculation.getNumber2()).isEqualTo(2);
        assertThat(calculation.getOperator()).isEqualTo("+");
        assertThat(calculation.getResult()).isEqualTo(12);

        assertThat(calculationRepository.isAdded()).isTrue();
    }

    @Test
    void minus() {
        Calculation calculation = calculator.calculate(10, 2, "-");

        assertThat(calculation.getNumber1()).isEqualTo(10);
        assertThat(calculation.getNumber2()).isEqualTo(2);
        assertThat(calculation.getOperator()).isEqualTo("-");
        assertThat(calculation.getResult()).isEqualTo(8);

        assertThat(calculationRepository.isAdded()).isTrue();
    }

    @Test
    void multiply() {
        Calculation calculation = calculator.calculate(10, 2, "*");

        assertThat(calculation.getNumber1()).isEqualTo(10);
        assertThat(calculation.getNumber2()).isEqualTo(2);
        assertThat(calculation.getOperator()).isEqualTo("*");
        assertThat(calculation.getResult()).isEqualTo(20);

        assertThat(calculationRepository.isAdded()).isTrue();
    }

    @Test
    void divide() {
        Calculation calculation = calculator.calculate(10, 2, "/");

        assertThat(calculation.getNumber1()).isEqualTo(10);
        assertThat(calculation.getNumber2()).isEqualTo(2);
        assertThat(calculation.getOperator()).isEqualTo("/");
        assertThat(calculation.getResult()).isEqualTo(5);

        assertThat(calculationRepository.isAdded()).isTrue();
    }

    @Test
    void divideByZero() {
        assertThatThrownBy(() -> {
            calculator.calculate(10, 0, "/");
        });

        assertThat(calculationRepository.isAdded()).isFalse();
    }
}
