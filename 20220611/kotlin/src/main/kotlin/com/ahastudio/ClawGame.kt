package com.ahastudio

fun main() {
    val board = arrayOf(
        arrayOf(" ", " ", " ", " ", " "),
        arrayOf(" ", " ", " ", " ", " "),
        arrayOf("A", " ", "B", " ", " "),
        arrayOf("B", "C", "B", "C", "C"),
        arrayOf("A", "A", "C", "C", "B"),
    )

    while (true) {
        printBoard(board);

        println()

        val position = inputPosition()

        val gift = pick(board, position)

        println()

        printGift(gift)

        println()
    }
}

fun printBoard(board: Array<Array<String>>) {
    for (row in board) {
        print("|")
        for (column in row) {
            print(column)
        }
        println("|")
    }
    println("+" + "-".repeat(5) + "+")
}

fun inputPosition(): Int {
    print("Input column number (1~5):")
    return readLine()!!.toInt()
}

fun pick(board: Array<Array<String>>, position: Int): String {
    if (position !in 1..board.size) {
        return ""
    }

    for (row in board) {
        val index = position - 1
        val column = row[index]
        if (column.isNotBlank()) {
            row[index] = " "
            return column
        }
    }

    return ""
}

fun printGift(gift: String) {
    when {
        gift.isBlank() -> println("There is nothing.")
        else -> println("You got ${gift}")
    }
}
