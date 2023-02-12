package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "process_steps")
public class Step {
    @Id
    private StepId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "process_id"))
    private ProcessId processId;

    private Integer sequence;

    private String name;

    @ElementCollection
    private final List<Applicant> applicants = new ArrayList<>();

    private Step() {
    }

    public Step(String name) {
        this.id = StepId.generate();
        this.name = name;
    }

    public void changeSequence(int sequence) {
        this.sequence = sequence;
    }

    public void addApplicant(String userId) {
        applicants.add(new Applicant(userId));
    }

    public boolean hasApplicant() {
        return !applicants.isEmpty();
    }

    public String name() {
        return name;
    }
}
