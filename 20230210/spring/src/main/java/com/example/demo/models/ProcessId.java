package com.example.demo.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import com.github.f4b6a3.tsid.TsidCreator;

@Embeddable
public class ProcessId {
    @Column(name = "id")
    private String value;

    private ProcessId() {
    }

    private ProcessId(String value) {
        this.value = value;
    }

    public static ProcessId generate() {
        return new ProcessId(TsidCreator.getTsid().toString());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ProcessId processId = (ProcessId) other;
        return Objects.equals(value, processId.value);
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
