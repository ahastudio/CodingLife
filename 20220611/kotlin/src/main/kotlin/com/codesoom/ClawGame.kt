package com.codesoom

fun main() {
    val board = arrayOf(
        arrayOf(" ", " ", " ", " ", " "),
        arrayOf(" ", " ", " ", " ", " "),
        arrayOf("A", " ", "B", " ", " "),
        arrayOf("B", "C", "B", "C", "C"),
        arrayOf("A", "A", "C", "C", "B"),
    )

    while (true) {
        printBoard(board)

        println()

        val position = inputPosition()

        println()

        val gift = pick(board, position)

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
    println("+" + "-".repeat(board[0].size) + "+")
}

fun inputPosition(): Int {
    print("Input column number (1~5): ")
    return readLine()!!.toInt()
}

fun pick(board: Array<Array<String>>, position: Int): String {
    val index = position - 1
    if (index !in board.indices) {
        return ""
    }

    for (row in board) {
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
        gift.isNotBlank() -> println("You got \"$gift\".")
        else -> println("There is nothing!")
    }
}
