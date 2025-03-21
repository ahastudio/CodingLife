package com.ahastudio.demo.shop.models;

import jakarta.persistence.Column;

public record ShopId(
        @Column(name = "id") String value
) {
    @Override
    public String toString() {
        return value;
    }
}
