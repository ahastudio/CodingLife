package com.ahastudio.demo.shop.models;

import jakarta.persistence.Column;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
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
        return String.valueOf(value);
    }
}