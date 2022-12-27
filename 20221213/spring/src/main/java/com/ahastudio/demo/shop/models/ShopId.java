package com.ahastudio.demo.shop.models;

import jakarta.persistence.Column;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ShopId {
    @Column(name = "id")
    private String value;

    private ShopId() {
    }

    public ShopId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
