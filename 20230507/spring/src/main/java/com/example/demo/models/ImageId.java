package com.example.demo.models;

import jakarta.persistence.Column;

public class ImageId {
    @Column(name = "id")
    private String value;

    private ImageId() {
    }

    public ImageId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
