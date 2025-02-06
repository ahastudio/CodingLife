package com.example.demo.application;

import com.example.demo.infrastructure.Calculation;

import java.util.List;

public interface CalculationRepository {
    void add(Calculation calculation);

    List<Calculation> getAll();
}
