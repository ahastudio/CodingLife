package com.example.demo.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import com.github.f4b6a3.tsid.TsidCreator;

@Embeddable
public class StepId {
    @Column(name = "id")
    private String value;

    private StepId() {
    }

    private StepId(String value) {
        this.value = value;
    }

    public static StepId generate() {
        return new StepId(TsidCreator.getTsid().toString());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        StepId stepId = (StepId) other;
        return Objects.equals(value, stepId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
