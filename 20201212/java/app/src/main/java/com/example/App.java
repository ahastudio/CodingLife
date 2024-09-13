package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(App.class);

        App app = context.getBean("myApp", App.class);

        System.out.println(app.getGreeting());
    }

    @Bean
    public App myApp() {
        return new App();
    }
}
