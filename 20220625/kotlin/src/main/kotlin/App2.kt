fun main() {
    val root = Node(value = 5)

    for (number in intArrayOf(3, 6, 4, 1, 8, 2, 7, 9, 10)) {
        root.add(Node(value = number))
    }

//    println(root.toMultilineString())

    val found = root.find(10)

    when (found) {
        0 -> println("Not found!")
        else -> println("Found: $found")
    }
}
