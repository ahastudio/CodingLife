package com.codesoom.demo.models;

public class Task {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return "Task > title: " + title;
    }
}
