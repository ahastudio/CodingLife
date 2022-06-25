import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NodeMultilineStringGeneratorTest {
    @Test
    fun `label이 없으면 빈 문자열 생성`() {
        val generator = NodeMultilineStringGenerator(depth = 0)

        assertEquals("", generator.label(label = ""))
    }

    @Test
    fun `label이 없으면 label에 해당하는 문자열 생성`() {
        val generator = NodeMultilineStringGenerator(depth = 1)

        assertEquals(
            "\n  Right:\n",
            generator.label(label = "Right")
        )
    }

    @Test
    fun `Node value 문자열 생성`() {
        val generator = NodeMultilineStringGenerator(depth = 0)
        assertEquals(
            "" +
                    "Node:\n" +
                    "  Value: 3",
            generator.nodeValue(value = 3)
        )
    }
}
