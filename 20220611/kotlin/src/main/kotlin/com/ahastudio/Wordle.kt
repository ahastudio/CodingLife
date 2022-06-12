package com.ahastudio

val words = arrayOf(
    "phone",
    "grade",
    "model",
    "major",
    "happy",
)

fun main() {
    val index = words.indices.random()
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

    for (i in answer.indices) {
        result += check(word, answer, i)
    }

    return result
}

fun check(word: String, answer: String, index: Int): String {
    if (answer[index] == word[index]) {
        return "O"
    }

    for (i in word.indices) {
        if (answer[index] == word[i]) {
            return "X"
        }
    }

    return "."
}
