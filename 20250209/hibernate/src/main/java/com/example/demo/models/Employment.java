package com.example.demo.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employments")
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DateRange period;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amount",
                    column = @Column(name = "salary_amount")
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "salary_currency")
            )
    })
    private Money salary;

    protected Employment() {
    }

    public Employment(DateRange period, Money salary) {
        this.period = period;
        this.salary = salary;
    }

    public Long id() {
        return id;
    }

    public DateRange period() {
        return period;
    }

    public Money salary() {
        return salary;
    }
}
