package com.codesoom.demo;

import com.codesoom.demo.models.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DemoHttpHandler implements HttpHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();

        System.out.println(method + " " + path);

        if (path.equals("/tasks")) {
            handleCollection(exchange, method);
            return;
        }

        if (path.startsWith("/tasks/")) {
            Long id = Long.parseLong(path.substring("/tasks/".length()));
            handleItem(exchange, method, id);
            return;
        }

        new ResponseSuccess(exchange).send("Hello, world!");
    }

    private void handleCollection(HttpExchange exchange, String method)
            throws IOException {
        if (method.equals("GET")) {
            handleList(exchange);
        }

        if (method.equals("POST")) {
            handleCreate(exchange);
        }
    }

    private void handleItem(HttpExchange exchange, String method, Long id)
            throws IOException {
        Task task = findTask(id);

        if (task == null) {
            new ResponseNotFound(exchange).send("");
            return;
        }

        if (method.equals("GET")) {
            handleDetail(exchange, task);
        }

        if (method.equals("PUT") || method.equals("PATCH")) {
            handleUpdate(exchange, task);
        }

        if (method.equals("DELETE")) {
            handleDelete(exchange, task);
        }
    }

    private void handleList(HttpExchange exchange) throws IOException {
        new ResponseSuccess(exchange).send(toJSON(tasks));
    }

    private void handleDetail(HttpExchange exchange, Task task)
            throws IOException {
        new ResponseSuccess(exchange).send(toJSON(task));
    }

    private void handleCreate(HttpExchange exchange) throws IOException {
        String body = getBody(exchange);

        Task task = toTask(body);
        task.setId(generateId());
        tasks.add(task);

        new ResponseCreated(exchange).send(toJSON(task));
    }

    private void handleUpdate(HttpExchange exchange, Task task) throws IOException {
        String body = getBody(exchange);
        Task source = toTask(body);

        task.setTitle(source.getTitle());

        new ResponseSuccess(exchange).send(toJSON(task));
    }

    private void handleDelete(HttpExchange exchange, Task task) throws IOException {
        tasks.remove(task);

        new ResponseSuccess(exchange).send("");
    }

    private void send(HttpExchange exchange, int statusCode, String content)
            throws IOException {
        exchange.sendResponseHeaders(statusCode, content.getBytes().length);

        OutputStream outputStream = exchange.getResponseBody();

        outputStream.write(content.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private String getBody(HttpExchange exchange) {
        InputStream inputStream = exchange.getRequestBody();
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private Task toTask(String content) throws JsonProcessingException {
        return objectMapper.readValue(content, Task.class);
    }

    private String toJSON(Object object) throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        objectMapper.writeValue(outputStream, object);

        return outputStream.toString();
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
