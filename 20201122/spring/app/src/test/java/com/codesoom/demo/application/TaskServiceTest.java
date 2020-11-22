package com.codesoom.demo.application;

import com.codesoom.demo.TaskNotFoundException;
import com.codesoom.demo.domain.Task;
import com.codesoom.demo.domain.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TaskServiceTest {
    // 1. list -> getTasks
    // 2. detail -> getTask (with ID)
    // 3. create -> createTask (with source)
    // 4. update -> updateTask (with ID, source)
    // 5. delete -> deleteTask (with ID)

    private static final String TASK_TITLE = "test";
    private static final String CREATE_POSTFIX = "...";
    private static final String UPDATE_POSTFIX = "!!!";

    private TaskService taskService;

    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);

        taskService = new TaskService(taskRepository);

        setUpFixtures();
        setUpSaveTask();
    }

    void setUpFixtures() {
        List<Task> tasks = new ArrayList<>();

        Task task = new Task();
        task.setTitle(TASK_TITLE);

        tasks.add(task);

        given(taskRepository.findAll()).willReturn(tasks);

        given(taskRepository.findById(1L)).willReturn(Optional.of(task));
        given(taskRepository.findById(100L)).willReturn(Optional.empty());
    }

    void setUpSaveTask() {
        given(taskRepository.save(any(Task.class))).will(invocation -> {
            Task task = invocation.getArgument(0);
            task.setId(2L);
            return task;
        });
    }

    @Test
    void getTasks() {
        List<Task> tasks = taskService.getTasks();

        verify(taskRepository).findAll();

        assertThat(tasks).hasSize(1);

        Task task = tasks.get(0);
        assertThat(task.getTitle()).isEqualTo(TASK_TITLE);
    }

    @Test
    void getTaskWithExistedId() {
        Task task = taskService.getTask(1L);

        verify(taskRepository).findById(1L);

        assertThat(task.getTitle()).isEqualTo(TASK_TITLE);
    }

    @Test
    void getTaskWithInvalidId() {
        assertThatThrownBy(() -> taskService.getTask(100L))
                .isInstanceOf(TaskNotFoundException.class);

        verify(taskRepository).findById(100L);
    }

    @Test
    void createTask() {
        Task source = new Task();
        source.setTitle(TASK_TITLE + CREATE_POSTFIX);

        Task task = taskService.createTask(source);

        verify(taskRepository).save(any(Task.class));

        assertThat(task.getId()).isEqualTo(2L);
        assertThat(task.getTitle()).isEqualTo(TASK_TITLE + CREATE_POSTFIX);
    }

    @Test
    void updateTaskWithExistedID() {
        Task source = new Task();
        source.setTitle(TASK_TITLE + UPDATE_POSTFIX);

        Task task = taskService.updateTask(1L, source);

        verify(taskRepository).findById(1L);

        assertThat(task.getTitle()).isEqualTo(TASK_TITLE + UPDATE_POSTFIX);
    }

    @Test
    void updateTaskWithNotExistedID() {
        Task source = new Task();
        source.setTitle(TASK_TITLE + UPDATE_POSTFIX);

        assertThatThrownBy(() -> taskService.updateTask(100L, source))
                .isInstanceOf(TaskNotFoundException.class);

        verify(taskRepository).findById(100L);
    }

    @Test
    void deleteTaskWithExsitedID() {
        taskService.deleteTask(1L);

        verify(taskRepository).findById(1L);
        verify(taskRepository).delete(any(Task.class));
    }

    @Test
    void deleteTaskWithNotExsitedID() {
        assertThatThrownBy(() -> taskService.deleteTask(100L))
                .isInstanceOf(TaskNotFoundException.class);

        verify(taskRepository).findById(100L);
    }
}
