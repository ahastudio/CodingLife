import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private Solution solution;

    @BeforeEach
    void setup() {
        solution = new Solution();
    }

    @Test
    void sample() {
        assertEquals(82, solution.solution(6, 4, 6, 2, new int[][]{
                {4, 1, 10},
                {3, 5, 24},
                {5, 6, 2},
                {3, 1, 41},
                {5, 1, 24},
                {4, 6, 50},
                {2, 4, 66},
                {2, 3, 22},
                {1, 6, 25}
        }));

        assertEquals(14, solution.solution(7, 3, 4, 1, new int[][]{
                {5, 7, 9},
                {4, 6, 4},
                {3, 6, 1},
                {3, 2, 3},
                {2, 1, 6}
        }));

        assertEquals(18, solution.solution(6, 4, 5, 6, new int[][]{
                {2, 6, 6},
                {6, 3, 7},
                {4, 6, 7},
                {6, 5, 11},
                {2, 5, 12},
                {5, 3, 20},
                {2, 4, 8},
                {4, 3, 9}
        }));
    }

    @Test
    void simple() {
        assertEquals(20, solution.solution(3, 1, 2, 3, new int[][]{
                {1, 2, 10},
                {1, 3, 10},
                {2, 3, 10}
        }));
    }

    @Test
    void fare() {
        solution.setFares(6, new int[][]{
                {4, 1, 10},
                {3, 5, 24},
                {5, 6, 2},
                {3, 1, 41},
                {5, 1, 24},
                {4, 6, 50},
                {2, 4, 66},
                {2, 3, 22},
                {1, 6, 25}
        });

        assertEquals(0, solution.fare(1, 1));

        assertEquals(10, solution.fare(4, 1));
        assertEquals(10, solution.fare(1, 4));
        assertEquals(24, solution.fare(3, 5));

        assertEquals(2 + 24, solution.fare(6, 3));
    }
}
