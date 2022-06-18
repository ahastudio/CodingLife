package com.codesoom

fun main() {
    println(sum(intArrayOf(1, 2, 3, 4, 5)))
    println(sum(intArrayOf(1, 2, 3, 4, 5, 6)))

    println(recursiveSum(intArrayOf(1, 2, 3, 4, 5, 6).toList()))
}

fun sum(numbers: IntArray): Int {
    var result: Int = 0

    for (i in numbers) {
        result += i
    }

    return result
}

// <<Recursive>>
// numbers:
// 1) 1, 2, 3
// 2) 2, 3
// 3) 3
// 4) empty
// return:
// 1) 1 + sum(intArrayOf(2, 3))
// 2) 2 + sum(intArrayOf(3))
// 3) 3 + sum(intArrayOf())
// 4) 0
fun recursiveSum(numbers: List<Int>): Int = when {
    numbers.isEmpty() -> 0
    else -> numbers[0] + recursiveSum(numbers.drop(1))
}
