package com.example.demo.models;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ADMIN_USER("ADMIN_USER");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
