package com.codesoom.demo;

import com.sun.net.httpserver.HttpExchange;

public class ResponseSuccess extends Response {
    public ResponseSuccess(HttpExchange exchange) {
        super(exchange);
    }

    @Override
    protected int httpStatusCode() {
        return 200;
    }
}
