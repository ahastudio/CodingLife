package com.ahastudio.api.rest.demo;

import java.text.DecimalFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NewtonMethodTest {
    private static final DecimalFormat DECIMAL_FORMAT =
            new DecimalFormat("0.######");

    private NewtonMethod sut;

    @BeforeEach
    void setUp() {
        sut = new NewtonMethod();
    }

    @Test
    void sqrt() {
        assertThat(DECIMAL_FORMAT.format(sut.sqrt(2)))
                .isEqualTo("1.414216");
        assertThat(DECIMAL_FORMAT.format(sut.sqrt(3)))
                .isEqualTo("1.732051");
        assertThat(DECIMAL_FORMAT.format(sut.sqrt(4)))
                .isEqualTo("2");
    }

    @Test
    void sqrtIter() {
        assertThat(DECIMAL_FORMAT.format(sut.sqrtIter(1, 2)))
                .isEqualTo("1.414216");
        assertThat(DECIMAL_FORMAT.format(sut.sqrtIter(1, 3)))
                .isEqualTo("1.732051");
        assertThat(DECIMAL_FORMAT.format(sut.sqrtIter(1, 4)))
                .isEqualTo("2");
    }

    @Test
    @DisplayName("goodeEnough 메서드는 guess 값이 제곱근이면 true를 리턴한다")
    void goodEnoughCorrect() {
        assertThat(sut.goodEnough(2, 4)).isTrue();
        assertThat(sut.goodEnough(1, 4)).isFalse();
    }

    @Test
    @DisplayName("goodeEnough 메서드는 guess 값이 제곱근과 비슷하면 true를 리턴한다")
    void goodEnoughNear() {
        assertThat(sut.goodEnough(1.9999999, 4)).isTrue();
    }

    @Test
    @DisplayName("goodeEnough 메서드는 guess 값이 음수면 false를 리턴한다")
    void goodEnoughNegative() {
        assertThat(sut.goodEnough(-1.9999999, 4)).isFalse();
    }

    @Test
    @DisplayName("goodeEnough 메서드는 0에 대해서도 처리할 수 있다")
    void goodEnoughZero() {
        assertThat(sut.goodEnough(0, 0)).isTrue();
        assertThat(sut.goodEnough(0, 1)).isFalse();
    }

    @Test
    void improve() {
        assertThat(DECIMAL_FORMAT.format(sut.improve(1, 2)))
                .isEqualTo("1.5");
        assertThat(DECIMAL_FORMAT.format(sut.improve(1.5, 2)))
                .isEqualTo("1.416667");
        assertThat(DECIMAL_FORMAT.format(sut.improve(1.416667, 2)))
                .isEqualTo("1.414216");
    }

    @Test
    void average() {
        assertThat(sut.average(1, 2)).isEqualTo(1.5);
        assertThat(sut.average(2, 2)).isEqualTo(2);
        assertThat(sut.average(0, 2)).isEqualTo(1);
        assertThat(sut.average(-2, 2)).isEqualTo(0);

        // 아래는 극한 실험
        assertThat(sut.average(-Double.MAX_VALUE, Double.MAX_VALUE))
                .isEqualTo(0);
//        assertThat(sut.average(Double.MAX_VALUE, Double.MAX_VALUE))
//                .isEqualTo(Double.MAX_VALUE);
        assertThat(sut.average(-Integer.MAX_VALUE, Integer.MAX_VALUE))
                .isEqualTo(0);
        assertThat(sut.average(Integer.MAX_VALUE, Integer.MAX_VALUE))
                .isEqualTo(Integer.MAX_VALUE);
    }
}
