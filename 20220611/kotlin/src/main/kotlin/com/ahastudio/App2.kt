package com.ahastudio

fun main() {
    var sum = 0

    // Range는 두 가지: x..y (y 포함) / x until y (y 제외)
    for (i in 0 until 10) {
        // 보호절 (guard clause)
        if (i % 2 == 0) {
            continue
        }

        if (i > 5) {
            break
        }

        println("Current number: $i")
        sum += i
    }

    println("Sum: $sum")
}
