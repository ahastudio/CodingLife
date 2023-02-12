package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "processes")
public class Process {
    @Id
    private ProcessId id;

    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "process_id")
    @OrderBy("sequence")
    private final List<Step> steps = new ArrayList<>();

    private Process() {
    }

    public Process(String title) {
        this.id = ProcessId.generate();
        this.title = title;
    }

    public Step addStep(String name) {
        return addStep(name, stepSize());
    }

    public Step addStep(String name, int index) {
        Step step = new Step(name);

        steps.add(index, step);

        resetStepsSequence(index);

        return step;
    }

    public void removeStep(int index) {
        Step step = steps.get(index);

        if (step.hasApplicant()) {
            throw new RuntimeException("Already applied");
        }

        steps.remove(index);

        resetStepsSequence(index);
    }

    public void addApplicant(int index, String userId) {
        Step step = steps.get(index);
        step.addApplicant(userId);
    }

    public int stepSize() {
        return steps.size();
    }

    public Step step(int index) {
        return steps.get(index);
    }

    private void resetStepsSequence(int index) {
        for (int i = index; i < stepSize(); i += 1) {
            step(i).changeSequence(i + 1);
        }
    }

    public ProcessId id() {
        return id;
    }
}
