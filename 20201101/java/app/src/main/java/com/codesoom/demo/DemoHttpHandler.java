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
    private static List<Task> tasks = new ArrayList<Task>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        URI uri = exchange.getRequestURI();
        String path = uri.getPath();

        InputStream inputStream = exchange.getRequestBody();
        String body = streamToString(inputStream);

        process(exchange, method, path, body);
    }

    private void process(
            HttpExchange exchange, String method, String path, String body
    ) throws IOException {
        System.out.println();
        System.out.println(method + " " + path);

        if (!body.isBlank()) {
            System.out.println(body);

            Task task = toTask(body);
            tasks.add(task);
        }

        String content = fromTask(tasks);

        send(exchange, 200, content);
    }

    private void send(HttpExchange exchange, int statusCode, String content)
            throws IOException {
        exchange.sendResponseHeaders(statusCode, content.getBytes().length);

        OutputStream outputStream = exchange.getResponseBody();
        writeToStream(outputStream, content);
    }

    private String streamToString(InputStream stream) {
        InputStreamReader streamReader = new InputStreamReader(stream);

        return new BufferedReader(streamReader)
                .lines()
                .collect(Collectors.joining("\n"));
    }

    private void writeToStream(OutputStream stream, String content)
            throws IOException {
        stream.write(content.getBytes());
        stream.flush();
        stream.close();
    }

    private Task toTask(String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Task task = objectMapper.readValue(content, Task.class);
        return task;
    }

    private String fromTask(List<Task> tasks) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        OutputStream outputStream = new ByteArrayOutputStream();
        objectMapper.writeValue(outputStream, tasks);

        return outputStream.toString();
    }
}
