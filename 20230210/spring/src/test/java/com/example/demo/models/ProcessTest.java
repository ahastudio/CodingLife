package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProcessTest {
    @Test
    void addStep() {
        Process process = new Process("B/E Programmer");

        process.addStep("Phone Interview");
        process.addStep("In-person Interview");

        Step step = process.addStep("Competency Assessment");

        assertThat(process.stepSize()).isEqualTo(3);

        assertThat(process.step(2)).isEqualTo(step);
    }

    @Test
    void addStepAt() {
        Process process = new Process("B/E Programmer");

        process.addStep("Phone Interview");
        process.addStep("In-person Interview");

        Step step = process.addStep("Competency Assessment", 1);

        assertThat(process.step(1)).isEqualTo(step);
    }

    @Test
    void removeStep() {
        Process process = new Process("B/E Programmer");

        process.addStep("Phone Interview");
        process.addStep("In-person Interview");
        Step step = process.addStep("Competency Assessment");

        process.addApplicant(2, "USER-ID");

        process.removeStep(1);

        assertThat(process.step(1)).isEqualTo(step);
    }

    @Test
    void removeStepFail() {
        Process process = new Process("B/E Programmer");

        process.addStep("Phone Interview");
        process.addStep("In-person Interview");
        process.addStep("Competency Assessment");

        process.addApplicant(2, "USER-ID");

        assertThatThrownBy(() -> {
            process.removeStep(2);
        });
    }
}
