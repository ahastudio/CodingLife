package com.codesoom.demo;

import com.sun.net.httpserver.HttpExchange;

public class ResponseNotFound extends Response {
    public ResponseNotFound(HttpExchange exchange) {
        super(exchange);
    }

    @Override
    protected int httpStatusCode() {
        return 404;
    }
}
