package com.codesoom.demo.infra;

import com.codesoom.demo.domain.Task;
import com.codesoom.demo.domain.TaskRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Primary
public interface JpaTaskRepository
        extends TaskRepository, CrudRepository<Task, Long> {
    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task save(Task task);

    void delete(Task task);
}
