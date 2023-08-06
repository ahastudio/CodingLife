package com.example.demo.models;

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

    public String toString() {
        return value;
    }
}
