package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageGeneratorTest {
    @Test
    void defaultText() {
        MessageGenerator messageGenerator = new MessageGenerator();

        assertEquals("Hello, world!", messageGenerator.text());
    }

    @Test
    void textWithName() {
        MessageGenerator messageGenerator = new MessageGenerator("Ashal");

        assertEquals("Hello, Ashal!", messageGenerator.text());
    }

    @Test
    void textWithEmptyName() {
        MessageGenerator messageGenerator = new MessageGenerator("");

        assertEquals("Hello, world!", messageGenerator.text());
    }

    @Test
    void textWithBlankName() {
        MessageGenerator messageGenerator = new MessageGenerator("   ");

        assertEquals("Hello, world!", messageGenerator.text());
    }
}
