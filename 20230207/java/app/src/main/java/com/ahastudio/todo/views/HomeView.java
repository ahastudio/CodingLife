package com.ahastudio.todo.views;

import java.util.List;
import java.util.stream.Collectors;

import com.ahastudio.todo.models.Task;

public class HomeView extends View {
    private final List<Task> tasks;

    public HomeView(List<Task> tasks) {
        this.tasks = tasks;
    }

    protected String content() {
        return String.format(
                """
                        <h2>To Do List</h2>
                        %s
                        %s
                        """,
                tasks(),
                taskForm()
        );
    }

    private String tasks() {
        if (tasks.isEmpty()) {
            return "<p>No task</p>";
        }

        return String.format(
                "<ul>%s</ul>",
                tasks.stream()
                        .map(HomeView::task)
                        .collect(Collectors.joining()));
    }

    private static String task(Task task) {
        return String.format(
                """
                        <li>
                            %s
                            <form method="POST" action="/complete-task">
                                <input type="hidden" name="id" value="%d">
                                <button type="submit">
                                    Complete
                                </button>
                            </form>
                        </li>
                        """,
                task.title(), task.id());
    }

    private String taskForm() {
        return """
                <form method="POST" action="/create-task">
                    <input type="hidden" name="key" value="value">
                    <input type="text" name="title">
                    <button type="submit">
                        Add
                    </button>
                </form>
                """;
    }
}
