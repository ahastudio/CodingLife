package com.example.demo.models;

import jakarta.persistence.Column;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ShopId {
    @Column(name = "id")
    private Long value;

    private ShopId() {
    }

    public ShopId(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
