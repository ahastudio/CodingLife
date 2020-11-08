package com.codesoom.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public String getGreeting() {
        return "Hello, world!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
