package com.codesoom.demo;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.stream.Collectors;

public class DemoHttpHandler implements HttpHandler {
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
        }

        String content = "Hello, world!";

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
}
