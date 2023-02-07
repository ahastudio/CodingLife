package com.ahastudio.todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.ahastudio.todo.handlers.CompleteTaskHandler;
import com.ahastudio.todo.handlers.CreateTaskHandler;
import com.ahastudio.todo.handlers.HomeHandler;
import com.ahastudio.todo.infrastructure.HttpServer;
import com.ahastudio.todo.models.Task;

public class App extends HttpServer implements Service {
    private final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.init();
        app.run();
    }

    private void init() {
        handlers.put(
                Pair.of("GET", "/"),
                new HomeHandler(this));
        handlers.put(
                Pair.of("POST", "/create-task"),
                new CreateTaskHandler(this));
        handlers.put(
                Pair.of("POST", "/complete-task"),
                new CompleteTaskHandler(this));
    }

    @Override
    public List<Task> tasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public void addTask(String title) {
        int id = tasks.stream().mapToInt(Task::id).max().orElse(0) + 1;

        Task task = new Task(id, title);

        tasks.add(task);
    }

    @Override
    public void completeTask(int id) {
        Optional<Task> found = tasks.stream()
                .filter(task -> task.id() == id)
                .findFirst();

        if (found.isEmpty()) {
            return;
        }

        tasks.remove(found.get());
    }
}
