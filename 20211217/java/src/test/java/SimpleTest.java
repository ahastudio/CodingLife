import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SimpleTest {
    @Test
    void success() {
        assertEquals(2, 1 + 1);
    }

    @Test
    @Disabled
    void fail() {
        assertEquals(1, 1 + 1);
    }
}
