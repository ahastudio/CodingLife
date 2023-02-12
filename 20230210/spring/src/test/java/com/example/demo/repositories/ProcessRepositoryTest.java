package com.example.demo.repositories;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Process;
import com.example.demo.models.ProcessId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ProcessRepositoryTest {
    private ProcessId processId;

    @Autowired
    private ProcessRepository processRepository;

    @BeforeEach
    @Transactional
    void setUp() {
        Process process = new Process("B/E Programmer");

        process.addStep("1");
        process.addStep("3");
        process.addStep("2", 1);

        process.addApplicant(2, "USER-ID");

        processRepository.save(process);

        processId = process.id();
    }

    @Test
    @Transactional
    void example() {
        Process process = processRepository.findById(processId).get();

        assertThat(process.stepSize()).isEqualTo(3);

        assertThat(process.step(0).name()).isEqualTo("1");
        assertThat(process.step(1).name()).isEqualTo("2");
        assertThat(process.step(2).name()).isEqualTo("3");

        assertThatThrownBy(() -> {
            process.removeStep(2);
        });

        process.removeStep(1);

        assertThat(process.stepSize()).isEqualTo(2);

        assertThat(process.step(0).name()).isEqualTo("1");
        assertThat(process.step(1).name()).isEqualTo("3");

        assertThatThrownBy(() -> {
            process.removeStep(1);
        });
    }
}
