// 1. Read Collection - GET /tasks => 완료
// 2. Read Item - GET /tasks/{id} => 완료
// 3. Create - POST /tasks => 완료
// 4. Update - PUT/PATCH /tasks/{id} => 완료
// 5. Delete - DELETE /tasks/{id} => 완료
// => 전제: Service가 올바른 것!

package com.codesoom.demo.controllers;

import com.codesoom.demo.TaskNotFoundException;
import com.codesoom.demo.application.TaskService;
import com.codesoom.demo.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class TaskControllerTest {
    private TaskController controller;

    // 가능한 것
    // 1. Real object
    // 2. Mock object => 타입이 필요함.
    // 3. Spy -> Proxy => 진짜 오브젝트가 필요함.
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = mock(TaskService.class);

        List<Task> tasks = new ArrayList<>();
        Task task = new Task();
        task.setTitle("Test1");
        tasks.add(task);

        given(taskService.getTasks()).willReturn(tasks);
        given(taskService.getTask(1L)).willReturn(task);
        given(taskService.getTask(100L))
                .willThrow(new TaskNotFoundException(100L));
        given(taskService.updateTask(eq(100L), any(Task.class)))
                .willThrow(new TaskNotFoundException(100L));
        given(taskService.deleteTask(100L))
                .willThrow(new TaskNotFoundException(100L));

        controller = new TaskController(taskService);
    }

    @Test
    void listWithoutTasks() {
        given(taskService.getTasks()).willReturn(new ArrayList<>());

        assertThat(controller.list()).isEmpty();

        verify(taskService).getTasks();
    }

    @Test
    void listWithSomeTasks() {
        assertThat(controller.list()).isNotEmpty();

        verify(taskService).getTasks();
    }

    @Test
    void detailWithExistedID() {
        Task task = controller.detail(1L);

        assertThat(task).isNotNull();
    }

    @Test
    void detailWithNotExistedID() {
        assertThatThrownBy(() -> controller.detail(100L))
                .isInstanceOf(TaskNotFoundException.class);
    }

    @Test
    void createNewTask() {
        Task task = new Task();
        task.setTitle("Test2");

        controller.create(task);

        verify(taskService).createTask(task);
    }

    @Test
    void updateExistedTask() {
        Task task = new Task();
        task.setTitle("Renamed task");

        controller.update(1L, task);

        verify(taskService).updateTask(1L, task);
    }


    @Test
    void updateNotExistedTask() {
        Task task = new Task();
        task.setTitle("Renamed task");

        assertThatThrownBy(() -> controller.update(100L, task))
                .isInstanceOf(TaskNotFoundException.class);
    }

    @Test
    void deleteExistedTask() {
        controller.delete(1L);

        verify(taskService).deleteTask(1L);
    }


    @Test
    void deleteNotExistedTask() {
        assertThatThrownBy(() -> controller.delete(100L))
                .isInstanceOf(TaskNotFoundException.class);
    }
}
