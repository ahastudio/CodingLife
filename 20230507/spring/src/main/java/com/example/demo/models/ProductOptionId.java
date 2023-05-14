package com.example.demo.models;

import jakarta.persistence.Column;

public class ProductOptionId {
    @Column(name = "id")
    private String value;

    private ProductOptionId() {
    }

    public ProductOptionId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
