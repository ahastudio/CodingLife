package com.example;

import com.sun.net.httpserver.HttpExchange;

import java.net.URI;

public class MyRequest {
    private final HttpExchange exchange;

    public MyRequest(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void print() {
        System.out.println("Request: " + getMethod() + " " + getPath());
    }

    public String getPath() {
        URI uri = exchange.getRequestURI();
        return uri.getPath();
    }

    public String getMethod() {
        return exchange.getRequestMethod();
    }
}
