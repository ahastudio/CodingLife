package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Gender {
    @Column(name = "gender")
    private String value;

    private Gender() {
    }

    private Gender(String value) {
        this.value = value;
    }

    public static Gender male() {
        return new Gender("남");
    }

    public static Gender female() {
        return new Gender("여");
    }

    @Override
    public String toString() {
        return value;
    }
}
