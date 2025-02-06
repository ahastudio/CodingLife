package com.example.demo.infrastructure;

import com.example.demo.application.CalculationRepository;

import java.util.List;

public class FakeCalculationRepository implements CalculationRepository {
    private boolean added = false;

    @Override
    public void add(Calculation calculation) {
        added = true;
    }

    @Override
    public List<Calculation> getAll() {
        return List.of();
    }

    public boolean isAdded() {
        return added;
    }
}
