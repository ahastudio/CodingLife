package com.ahastudio.todo;

import java.util.List;

import com.ahastudio.todo.models.Task;

public interface Service {
    List<Task> tasks();

    void addTask(String title);

    void completeTask(int id);
}
