package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {
    @Test
    void appHasAGreeting() {
        App classUnderTest = new App();

        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");

        assertEquals("Hello, world!", classUnderTest.getGreeting());
    }

    @Test
    void scanContentLength() {
        App app = new App();

        int length = app.scanContentLength("""
                HTTP/1.1 200 OK
                Content-Type: text/html; charset=UTF-8
                Content-Length: 1256
                """);

        assertEquals(1256, length);
    }
}
