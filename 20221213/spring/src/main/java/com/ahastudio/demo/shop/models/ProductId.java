package com.ahastudio.demo.shop.models;

import jakarta.persistence.Column;

public record ProductId(
        @Column(name = "id") String value
) {
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
