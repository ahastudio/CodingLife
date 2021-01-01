package com.ahastudio.components;

import org.springframework.stereotype.Component;

@Component
public class InjectSimpleConfig {
    public String getName() {
        return "John Mayer";
    }

    public int getAge() {
        return 39;
    }
}
