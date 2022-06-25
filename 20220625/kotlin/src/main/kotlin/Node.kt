class Node(val value: Int) {
    val text: String
        get() {
            return "[Node: $value / Left: $left / Right: $right]"
        }

    var left: Node? = null
        // 아래 둘은 없어도 됨. 기본 형태임.
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var right: Node? = null

    override fun toString(): String {
        return this.text
    }

    fun toMultilineString(label: String = "", depth: Int = 0): String {
        val generator = NodeMultilineStringGenerator(depth = depth)
        return generator.generate(label = label, node = this)
    }

    fun add(node: Node) {
        val isLeft = node.value < value

        val direction = if (isLeft) "left" else "right"
        val child = if (isLeft) left else right

        when (child) {
            null -> changeChild(direction, node)
            else -> child.add(node)
        }
    }

    private fun changeChild(direction: String, node: Node) {
        when (direction) {
            "left" -> this.left = node
            "right" -> this.right = node
        }
    }

    fun find(value: Int): Int {
        val node = search(value)

        return when (node) {
            null -> 0
            else -> node.value
        }
    }

    fun search(value: Int): Node? {
        println(this.value)

        return when {
            value < this.value -> left?.search(value)
            value > this.value -> right?.search(value)
            value == this.value -> this
            else -> null
        }
    }
}
