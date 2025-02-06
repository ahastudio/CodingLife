package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        MyRequest request = new MyRequest(exchange);
        MyResponse response = new MyResponse(exchange);

        request.print();
        response.send(request.getPath() + "\nHello, world!\n");
    }
}
