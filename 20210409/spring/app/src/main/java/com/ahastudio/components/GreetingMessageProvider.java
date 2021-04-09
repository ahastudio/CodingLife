package com.ahastudio.components;

import org.springframework.stereotype.Service;

@Service("provider")
public class GreetingMessageProvider implements MessageProvider {
    private String name = "World";

    public GreetingMessageProvider() {
    }

    public GreetingMessageProvider(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "Hello, " + name + "!";
    }
}
