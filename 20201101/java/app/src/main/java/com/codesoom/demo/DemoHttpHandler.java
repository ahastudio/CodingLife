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

    private Long newId = 1L;
    private List<Task> tasks = new ArrayList<>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // REST - CRUD
        // 1. Method - GET, POST, PUT/PATCH, DELETE, ...
        // 2. Path - "/", "/tasks", "/tasks/1", ...
        // 3. Headers, Body(Content)

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

        send(exchange, 200, "Hello, world!");
    }

    private void handleCollection(HttpExchange exchange, String method)
            throws IOException {
        if (method.equals("GET")) {
            handleList(exchange);
        }

        if (method.equals("POST")) {
            String body = getBody(exchange);
            handleCreate(exchange, body);
        }
    }

    private void handleItem(HttpExchange exchange, String method, Long id)
            throws IOException {
        if (method.equals("GET")) {
            handleDetail(exchange, id);
        }

        if (method.equals("PUT") || method.equals("PATCH")) {
            String body = getBody(exchange);
            handleUpdate(exchange, id, body);
        }

        if (method.equals("DELETE")) {
            handleDelete(exchange, id);
        }
    }

    private void handleList(HttpExchange exchange) throws IOException {
        send(exchange, 200, toJSON(tasks));
    }

    private void handleDetail(HttpExchange exchange, Long id)
            throws IOException {
        Task task = findTask(id);

        if (task == null) {
            send(exchange, 404, "");
            return;
        }

        send(exchange, 200, toJSON(task));
    }

    private void handleCreate(HttpExchange exchange, String body)
            throws IOException {
        Task task = toTask(body);
        task.setId(newId);
        newId += 1;

        tasks.add(task);

        send(exchange, 201, toJSON(task));
    }

    private void handleUpdate(HttpExchange exchange, Long id, String body)
            throws IOException {
        Task task = findTask(id);

        if (task == null) {
            send(exchange, 404, "");
            return;
        }

        Task source = toTask(body);

        task.setTitle(source.getTitle());

        send(exchange, 200, toJSON(task));
    }

    private void handleDelete(HttpExchange exchange, Long id)
            throws IOException {
        Task task = findTask(id);

        if (task == null) {
            send(exchange, 404, "");
            return;
        }

        tasks.remove(task);

        send(exchange, 200, "");
    }

    private String getBody(HttpExchange exchange) {
        InputStream inputStream = exchange.getRequestBody();
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    private void send(HttpExchange exchange, int code, String content)
            throws IOException {
        exchange.sendResponseHeaders(code, content.getBytes().length);

        OutputStream outputStream = exchange.getResponseBody();

        outputStream.write(content.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst().orElse(null);
    }

    private Task toTask(String content) throws JsonProcessingException {
        return objectMapper.readValue(content, Task.class);
    }

    private String toJSON(Object object) throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        objectMapper.writeValue(outputStream, object);

        return outputStream.toString();
    }
}
