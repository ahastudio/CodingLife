package com.codesoom

fun main() {
    // Range: 1..10 => 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    // 임의의 숫자들 -> 배열(Array)
    // 타입: Array<T> -> generics -> Array<Int>, Array<String>
    //      -> IntArray, DoubleArray

    // val numbers = Array<Int>(5) { it + 1 }
    // val numbers = arrayOf(1, 3, 7, 10)
    val numbers = intArrayOf(1, 3, 7, 10, 20)

    val temp = numbers[0]
    numbers[0] = numbers[2]
    numbers[2] = temp

    var sum = 0

    for (i in 0 until numbers.size) {
        println("$i - ${numbers[i]}")
        sum += numbers[i]
    }

    println("Sum: $sum")

    val message = "Hello"

    for (i in 0 until message.length) {
        when (i % 3) {
            0 -> println(message[i].uppercase())
            1 -> println(message[i])
            2 -> println("*")
        }
    }
}
