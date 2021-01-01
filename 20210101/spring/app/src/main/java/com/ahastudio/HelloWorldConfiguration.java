package com.ahastudio;

import com.ahastudio.components.Singer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {
    @Bean(name = {"john-mayer", "john", "jonathan", "johnny"})
    public Singer singer() {
        return new Singer();
    }
}
