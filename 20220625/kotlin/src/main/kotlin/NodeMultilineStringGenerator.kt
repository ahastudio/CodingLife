class NodeMultilineStringGenerator(val depth: Int) {
    fun generate(label: String, node: Node): String {
        return this.label(label = label) +
                this.nodeValue(value = node.value) +
                this.child(node = node.left, label = "Left") +
                this.child(node = node.right, label = "Right")
    }

    fun label(label: String): String {
        if (label.isBlank()) {
            return ""
        }

        val indent = " ".repeat(4 * (depth - 1))
        return "\n" + indent + "  $label:\n"
    }

    fun nodeValue(value: Int): String {
        val indent = " ".repeat(4 * depth)
        return indent + "Node:\n" +
                indent + "  Value: $value"
    }

    fun child(node: Node?, label: String): String {
        if (node == null) {
            return ""
        }

        return node.toMultilineString(label = label, depth = depth + 1)
    }
}
