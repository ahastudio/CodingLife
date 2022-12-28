package com.ahastudio.http.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run();
    }

    private void run() throws IOException {
        InetSocketAddress address = new InetSocketAddress(8080);
        HttpServer httpServer = HttpServer.create(address, 0);

        httpServer.createContext("/", exchange -> {
            displayRequest(exchange);

            String content = "Hello, world!\n";
            sendContent(exchange, content);
        });

        httpServer.createContext("/hi", exchange -> {
            displayRequest(exchange);

            String content = "Hi, world!\n";
            sendContent(exchange, content);
        });

        httpServer.start();
    }

    private void displayRequest(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        System.out.println("Method: " + method);

        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        System.out.println("Path: " + path);

        Headers headers = exchange.getRequestHeaders();
        for (String key : headers.keySet()) {
            List<String> values = headers.get(key);
            System.out.println(key + ": " + values);
        }

        InputStream inputStream = exchange.getRequestBody();
        String body = new String(inputStream.readAllBytes());

        System.out.println(body);
    }

    private void sendContent(HttpExchange exchange, String content) throws IOException {
        byte[] bytes = content.getBytes();

        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(bytes);
        outputStream.flush();
    }
}
