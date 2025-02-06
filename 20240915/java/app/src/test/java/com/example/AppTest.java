package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {
    @Test
    void createApp() {
        App app = new App();
        assertNotNull(app);
    }
}
