package com.ahastudio.sample;

public class App {
    public String getGreeting() {
        return "Hello";
    }

    public static void main(String[] args) {
        final App app = new App();
        System.out.println(app.getGreeting());
    }
}
