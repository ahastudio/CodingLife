package com.example.demo.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class CalculationRepository {
    private final List<Calculation> calculations = new ArrayList<>();

    private static CalculationRepository instance = null;

    protected CalculationRepository() {
    }

    public static CalculationRepository getInstance() {
        if (instance == null) {
            instance = new CalculationRepository();
        }
        return instance;
    }

    public void add(Calculation calculation) {
        calculations.add(calculation);
    }

    public List<Calculation> getAll() {
        return new ArrayList<>(calculations);
    }
}
