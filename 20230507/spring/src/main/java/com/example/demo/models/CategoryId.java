package com.example.demo.models;

import jakarta.persistence.Column;

public class CategoryId {
    @Column(name = "id")
    private String value;

    private CategoryId() {
    }

    public CategoryId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
