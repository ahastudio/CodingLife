package com.ahastudio.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {
    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();
    }

    @Test
    public void greeting() {
        assertEquals("Hello", app.getGreeting());
    }
}
