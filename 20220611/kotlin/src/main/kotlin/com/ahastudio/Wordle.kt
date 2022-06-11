package com.ahastudio

val words = arrayOf(
    "phone",
    "grade",
    "model",
    "major",
)

fun main() {
    val index = (0 until words.size).random()
    val word = words[index]

    while (true) {
        println("Guess?")

        val answer = readLine()!!

        if (answer.length != word.length) {
            println("Word is ${word.length} length")
            continue
        }

        println(check(answer, word))

        if (answer == word) {
            println("You win!")
            break
        }
    }
}

fun check(answer: String, word: String): String {
    var result = ""
    for (i in 0 until answer.length) {
        result += check(word, answer, i)
    }
    return result
}

fun check(word: String, answer: String, index: Int): String {
    for (i in 0 until word.length) {
        if (answer[index] != word[i]) {
            continue
        }
        return when {
            index == i -> "O"
            else -> "X"
        }
    }
    return "."
}
