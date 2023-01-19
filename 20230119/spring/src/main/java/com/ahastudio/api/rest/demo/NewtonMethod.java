package com.ahastudio.api.rest.demo;

public class NewtonMethod {
    private static final double EPSILON = Math.pow(0.1, 5);

    public double sqrt(double x) {
        return sqrtIter(1, x);
    }

    public double sqrtIter(double guess, double x) {
        if (goodEnough(guess, x)) {
            return guess;
        }

        return sqrtIter(improve(guess, x), x);
    }

    /**
     * 제곱근의 후보가 적당히 괜찮은지 확인한다.
     *
     * @param guess 제곱근의 후보
     * @param x     제곱근을 구하려는 원래 숫자
     * @return 제곱근의 후보가 적당히 괜찮은 값이면 true
     */
    public boolean goodEnough(double guess, double x) {
        return guess >= 0 && Math.abs(Math.pow(guess, 2) - x) < EPSILON;
    }

    public double improve(double guess, double x) {
        return average(guess, (x / guess));
    }

    public double average(double x, double y) {
        return (x + y) / 2;
    }
}
