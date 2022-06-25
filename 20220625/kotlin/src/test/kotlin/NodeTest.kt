import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NodeTest {
    @Test
    fun `add를 통해 Left 추가`() {
        val node = Node(value = 3)
        val child = Node(value = 1)

        node.add(child)

        assertEquals(child, node.left)
    }

    @Test
    fun `add를 통해 Left의 Left에 추가`() {
        val node = Node(value = 3)
        val child1 = Node(value = 2)
        val child2 = Node(value = 1)

        node.add(child1)
        node.add(child2)

        assertEquals(child1, node.left)
        assertEquals(child2, node.left?.left)
    }

    @Test
    fun `add를 통해 Left의 Right에 추가`() {
        val node = Node(value = 5)
        val child1 = Node(value = 3)
        val child2 = Node(value = 4)

        node.add(child1)
        node.add(child2)

        assertEquals(child1, node.left)
        assertEquals(child2, node.left?.right)
    }

    @Test
    fun `add를 통해 Right 추가`() {
        val node = Node(value = 3)
        val child = Node(value = 5)

        node.add(child)

        assertEquals(child, node.right)
    }

    @Test
    fun `add를 통해 Right의 Right에 추가`() {
        val node = Node(value = 3)
        val child1 = Node(value = 4)
        val child2 = Node(value = 5)

        node.add(child1)
        node.add(child2)

        assertEquals(child1, node.right)
        assertEquals(child2, node.right?.right)
    }

    @Test
    fun `add를 통해 Right의 Left에 추가`() {
        val node = Node(value = 3)
        val child1 = Node(value = 8)
        val child2 = Node(value = 5)

        node.add(child1)
        node.add(child2)

        assertEquals(child1, node.right)
        assertEquals(child2, node.right?.left)
    }

    @Test
    fun `find는 BST에 있는 값을 찾는다`() {
        val node = Node(value = 5)
        node.add(Node(value = 3))

        assertEquals(3, node.find(3))
    }

    @Test
    fun `값을 찾을 수 없음`() {
        val node = Node(value = 5)

        assertEquals(null, node.search(30))
    }

    @Test
    fun `나 자신 찾기`() {
        val node = Node(value = 5)

        assertEquals(node, node.search(5))
    }

    @Test
    fun `나보다 작으면 Left에서 찾기`() {
        val node = Node(value = 5)
        val child = Node(value = 3)

        node.add(child)

        assertEquals(child, node.search(3))
    }

    @Test
    fun `나보다 크면 Right에서 찾기`() {
        val node = Node(value = 5)
        val child = Node(value = 8)

        node.add(child)

        assertEquals(child, node.search(8))
    }

    @Test
    fun `multilineString은 Node를 여러 줄로 표현한다`() {
        val node = Node(value = 3)

        assertEquals("Node:\n  Value: 3", node.toMultilineString())
    }

    @Test
    fun `multilineString은 Left node를 표현한다`() {
        val node = Node(value = 3)
        node.left = Node(value = 1)

        assertEquals(
            "" +
                    "Node:\n" +
                    "  Value: 3\n" +
                    "  Left:\n" +
                    "    Node:\n" +
                    "      Value: 1",
            node.toMultilineString()
        )
    }

    @Test
    fun `multilineString은 더 깊은 Left node를 표현한다`() {
        val node = Node(value = 3)
        val child = Node(value = 2)

        node.left = child
        child.left = Node(value = 1)

        assertEquals(
            "" +
                    "Node:\n" +
                    "  Value: 3\n" +
                    "  Left:\n" +
                    "    Node:\n" +
                    "      Value: 2\n" +
                    "      Left:\n" +
                    "        Node:\n" +
                    "          Value: 1",
            node.toMultilineString()
        )
    }

    @Test
    fun `multilineString은 Right node를 표현한다`() {
        val node = Node(value = 3)
        node.right = Node(value = 6)

        assertEquals(
            "" +
                    "Node:\n" +
                    "  Value: 3\n" +
                    "  Right:\n" +
                    "    Node:\n" +
                    "      Value: 6",
            node.toMultilineString()
        )
    }

    @Test
    fun `multilineString은 더 깊은 Right node를 표현한다`() {
        val node = Node(value = 3)
        val child = Node(value = 6)

        node.right = child
        child.right = Node(value = 10)

        assertEquals(
            "" +
                    "Node:\n" +
                    "  Value: 3\n" +
                    "  Right:\n" +
                    "    Node:\n" +
                    "      Value: 6\n" +
                    "      Right:\n" +
                    "        Node:\n" +
                    "          Value: 10",
            node.toMultilineString()
        )
    }

    @Test
    fun `Left 또는 Right를 표현하는 multiline string 확인`() {
        val node = Node(value = 6)

        assertEquals(
            "\n" +
                    "  Right:\n" +
                    "    Node:\n" +
                    "      Value: 6",
            node.toMultilineString(label = "Right", depth = 1)
        )
    }
}
