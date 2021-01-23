package com.codesoom.demo;

public enum HttpStatus {
    OK(200, "Success"),
    CREATED(201, "Created"),
    NOT_FOUND(404, "Not Found");

    private final int value;
    private final String text;

    HttpStatus(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value() {
        return value;
    }

    public String toString() {
        return text;
    }
}
