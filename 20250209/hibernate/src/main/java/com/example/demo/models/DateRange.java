package com.example.demo.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record DateRange(
        @Column(name = "start")
        LocalDate startDate,

        @Column(name = "\"end\"")
        LocalDate endDate
) {
}
