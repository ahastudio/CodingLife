package com.codesoom.demo;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public abstract class Response {
    private final HttpExchange exchange;

    Response(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void send(String content) throws IOException {
        exchange.sendResponseHeaders(
                httpStatusCode(), content.getBytes().length);

        OutputStream outputStream = exchange.getResponseBody();

        outputStream.write(content.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    protected abstract int httpStatusCode();
}
