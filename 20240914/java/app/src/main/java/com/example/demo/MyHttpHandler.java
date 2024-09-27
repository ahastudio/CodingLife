package com.example.demo;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        MyRequest request = new MyRequest(exchange);
        MyResponse response = new MyResponse(exchange);

        request.print();
        response.send("Hello, world!");
    }
}
