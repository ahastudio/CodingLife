import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {
    private HelloWorld helloWorld;

    @BeforeEach
    void setUp() {
        helloWorld = new HelloWorld();
    }

    @Test
    void greeting() {
        assertEquals("Hello, world!", helloWorld.greeting());
    }
}
