package com.example.demo.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Table(name = "applicants")
public class Applicant {
    private String userId;

    private Applicant() {
    }

    public Applicant(String userId) {
        this.userId = userId;
    }
}
