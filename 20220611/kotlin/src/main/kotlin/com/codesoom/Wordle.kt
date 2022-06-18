package com.codesoom

val words = arrayOf(
    "phone",
    "grade",
    "model",
    "major",
    "happy",
)

fun main() {
    val word = words.random()

    var correct = false

    while (!correct) {
        val answer = inputAnswer()

        if (!isValid(word = word, answer = answer)) {
            continue
        }

        val result = check(word = word, answer = answer)

        printResult(result)

        correct = isCorrect(word = word, answer = answer)
    }

    println("You win!")
}

fun inputAnswer(): String {
    println("Guess?!")
    return readLine()!!
}

fun isValid(word: String, answer: String): Boolean {
    if (answer.length == word.length) {
        return true
    }

    println("Word is 5 letters.")
    return false
}

fun check(word: String, answer: String): String {
    var result = ""

    for (i in answer.indices) {
        result += checkCharacter(word = word, answer = answer, index = i)
    }

    return result
}

fun checkCharacter(word: String, answer: String, index: Int): String {
    val x = answer[index]

    if (x == word[index]) {
        return "O"
    }

    for (y in word) {
        if (x == y) {
            return "X"
        }
    }

    return "."
}

fun printResult(result: String) {
    println("Result: $result")
}

fun isCorrect(word: String, answer: String): Boolean {
    return answer == word
}
