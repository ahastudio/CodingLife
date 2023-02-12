package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Process;
import com.example.demo.models.ProcessId;

public interface ProcessRepository extends JpaRepository<Process, ProcessId> {
}
