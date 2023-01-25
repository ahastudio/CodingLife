// TODO
// 1. Read Collection - GET /tasks => 완료
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks => 완료
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.codesoom.demo.models.Task;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final List<Task> tasks = new ArrayList<>();

    private Long newId = 0L;

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task detail(@PathVariable Long id) {
        return findTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        Task found = findTask(id);

        found.setTitle(task.getTitle());

        return found;
    }

    @PatchMapping("/{id}")
    public Task patch(@PathVariable Long id, @RequestBody Task task) {
        Task found = findTask(id);

        found.setTitle(task.getTitle());

        return found;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Task task = findTask(id);

        tasks.remove(task);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
