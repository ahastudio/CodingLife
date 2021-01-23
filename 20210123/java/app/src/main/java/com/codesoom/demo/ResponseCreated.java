package com.codesoom.demo;

import com.sun.net.httpserver.HttpExchange;

public class ResponseCreated extends Response {
    public ResponseCreated(HttpExchange exchange) {
        super(exchange);
    }

    @Override
    protected int httpStatusCode() {
        return 201;
    }
}
