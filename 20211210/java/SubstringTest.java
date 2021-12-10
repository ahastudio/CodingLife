import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubstringTest {
    private Substring substring;

    @BeforeEach
    void setup() {
        substring = new Substring();
    }

    @Test
    void sample() {
        // start는 포함, end는 포함하지 않음.
        assertEquals("lowo", substring.substring("Helloworld", 3, 7));
    }

    @Test
    void simple() {
        assertEquals("", substring.substring("Hello", 0, 0));
        assertEquals("H", substring.substring("Hello", 0, 1));
        assertEquals("He", substring.substring("Hello", 0, 2));
    }

    @Test
    void range() {
        assertEquals("", substring.substring("Hello", 1, 1));
        assertEquals("e", substring.substring("Hello", 1, 2));
        assertEquals("el", substring.substring("Hello", 1, 3));
    }

    @Test
    void error() {
        assertEquals("", substring.substring("Hello", 1, 0));
        assertEquals("", substring.substring("12345", 5, 10));
        assertEquals("45", substring.substring("12345", 3, 10));
        assertEquals("12", substring.substring("12345", -10, 2));
    }
}
