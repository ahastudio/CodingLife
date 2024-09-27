package com.example.demo;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class MyResponse {
    private final HttpExchange exchange;

    public MyResponse(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void send(String content) throws IOException {
        if (content.isEmpty()) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        byte[] bytes = content.getBytes();

        exchange.sendResponseHeaders(200, bytes.length);

        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(bytes);
        }
    }
}
