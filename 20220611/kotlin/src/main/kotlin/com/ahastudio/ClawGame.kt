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
        // TODO: board를 화면에 그려준다.

        print("Input column number (1~5):")
        val position = readLine()!!.toInt()

        // TODO: 입력 받은 위치의 상품을 꺼내고, 결과를 보여준다.
    }
}
