package com.example.demo.models;

import jakarta.persistence.Column;

public class ProductOptionItemId {
    @Column(name = "id")
    private String value;

    private ProductOptionItemId() {
    }

    public ProductOptionItemId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
