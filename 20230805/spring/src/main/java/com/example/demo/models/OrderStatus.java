package com.example.demo.models;

import java.util.Arrays;

public enum OrderStatus {
    PAID("paid"),
    READY("ready"),
    SHIPPING("shipping"),
    COMPLETE("complete"),
    CANCELED("canceled");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public static OrderStatus of(String value) {
        return Arrays.stream(OrderStatus.values())
                .filter(orderStatus -> orderStatus.value.equals(value))
                .findFirst()
                .orElseThrow();
    }

    public String toString() {
        return value;
    }
}
