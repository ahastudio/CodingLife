package com.example.demo.presentation;

import org.springframework.stereotype.Component;

@Component
public class HomeGetHandler extends ResourceMethodHandler {
    @Override
    public String key() {
        return "GET /";
    }

    @Override
    public String handle(String content) {
        return "Hello, world!\n";
    }
}
