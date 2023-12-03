package com.example.chapter02

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

//fun sum(n: Long): Long =
//    if (n == 0L) 0
//    else sum(n - 1) + n

fun sum(n: Long): Long {
    tailrec fun go(n: Long, acc: Long): Long =
        if (n == 0L) acc
        else go(n - 1, acc + n)

    return go(n, 0)
}

fun fib(n: Long): Long {
    if (n <= 1)
        return n

    tailrec fun go(n: Long, acc: Long, previous: Long): Long =
        if (n == 1L) acc
        else go(n - 1, acc + previous, acc)

    return go(n, 1, 0)
}

// 확장 프로퍼티
val <T> List<T>.head: T
    get() = first()
val <T> List<T>.tail: List<T>
    get() = drop(1)

fun <A> isSorted(xs: List<A>, order: (A, A) -> Boolean): Boolean {
    if (xs.isEmpty())
        return true

    tailrec fun go(head: A, tail: List<A>): Boolean = when {
        tail.isEmpty() -> true
        !order(head, tail.head) -> false
        else -> go(tail.head, tail.tail)
    }

    return go(xs.head, xs.tail)
}

fun <A, B, C> partial(a: A, f: (A, B) -> C): (B) -> C =
    { b: B -> f(a, b) }

fun <A, B, C> currying(f: (A, B) -> C): (A) -> (B) -> C =
    { a: A -> { b: B -> f(a, b) } }

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C =
    { x: A -> f(g(x)) }

class Chapter02Test {
    @Test
    fun testSum() {
        assertEquals(1, sum(1))
        assertEquals(3, sum(2))
        assertEquals(6, sum(3))

        assertEquals(5_000_050_000, sum(100_000))
    }

    @Test
    fun testFib() {
        // 기본 값 깔아주기.
        assertEquals(0, fib(0))
        assertEquals(1, fib(1))

        // 여기부터가 진짜!
        assertEquals(1, fib(2))
        assertEquals(2, fib(3))
        assertEquals(3, fib(4))
        assertEquals(5, fib(5))
        assertEquals(8, fib(6))
        assertEquals(13, fib(7))

        assertEquals(2754320626097736315, fib(100_000))
    }

    @Test
    fun testIsSortedIntList() {
//        fun order(a: Int, b: Int) = a <= b
        val order = { a: Int, b: Int -> a <= b }

        assertTrue(isSorted(emptyList(), order))
        assertTrue(isSorted(listOf(1, 2, 3), order))
        assertFalse(isSorted(listOf(1, 3, 2), order))
        assertFalse(isSorted(listOf(2, 1, 3), order))
    }

    @Test
    fun testIsSortedStringList() {
        val order = { a: String, b: String -> a <= b }

        assertTrue(isSorted(emptyList(), order))
        assertTrue(isSorted(listOf("A", "B", "C"), order))
        assertFalse(isSorted(listOf("A", "C", "B"), order))
        assertFalse(isSorted(listOf("B", "A", "C"), order))
    }

    @Test
    fun testPartial() {
        val multiply = { a: Int, b: Int -> (a * b).toString() }

        assertEquals("6", multiply(2, 3))

        val double = partial(2, multiply)

        assertEquals("4", double(2))
        assertEquals("6", double(3))
        assertEquals("8", double(4))
    }

    @Test
    fun testCurrying() {
        val multiply = { a: Int, b: Int -> (a * b).toString() }

        val double = currying(multiply)(2)

        assertEquals("4", double(2))
        assertEquals("6", double(3))
        assertEquals("8", double(4))
    }

    @Test
    fun testCompose() {
        val double = { x: Int -> x * 2 }
        val toString = { x: Int -> x.toString() }

        assertEquals("4", compose(toString, double)(2))
        assertEquals("6", compose(toString, double)(3))
        assertEquals("8", compose(toString, double)(4))
    }
}
