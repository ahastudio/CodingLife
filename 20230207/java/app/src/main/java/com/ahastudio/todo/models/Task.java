package com.ahastudio.todo.models;

public class Task {
    private final int id;

    private final String title;

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int id() {
        return id;
    }

    public String title() {
        return title;
    }
}
