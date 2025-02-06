package com.example.demo.infrastructure;

import com.example.demo.application.CalculationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryCalculationRepository implements CalculationRepository {
    private final List<Calculation> calculations = new ArrayList<>();

    @Override
    public void add(Calculation calculation) {
        calculations.add(calculation);
    }

    @Override
    public List<Calculation> getAll() {
        return new ArrayList<>(calculations);
    }
}
