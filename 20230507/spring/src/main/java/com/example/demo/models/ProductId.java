package com.example.demo.models;

import jakarta.persistence.Column;

public class ProductId {
    @Column(name = "id")
    private String value;

    private ProductId() {
    }

    public ProductId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
