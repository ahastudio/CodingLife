package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {
    @Test
    void simple() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest);
    }
}
