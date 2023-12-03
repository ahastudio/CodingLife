package com.example.chapter03

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class Chapter03Test {
    @Test
    fun testList() {
        assertEquals(Cons(1, Nil), List.of(1))
        assertEquals(Cons(1, Cons(2, Nil)), List.of(1, 2))
        assertEquals(Cons(1, Cons(2, Cons(3, Nil))), List.of(1, 2, 3))
    }

    @Test
    fun testSum() {
        assertEquals(15, List.sum(List.of(1, 2, 3, 4, 5)))
    }

    @Test
    fun testProduct() {
        assertEquals(24, List.product(List.of(2, 3, 4)))
    }

    @Test
    fun testTail() {
        assertEquals(Nil, List.tail(Nil))
        assertEquals(List.of(2, 3), List.tail(List.of(1, 2, 3)))
    }

    @Test
    fun testSetHead() {
        assertEquals(List.of(4), List.setHead(Nil, 4))
        assertEquals(List.of(4, 2, 3), List.setHead(List.of(1, 2, 3), 4))
    }

    @Test
    fun testFoldRight() {
        val add = { cur: Int, acc: Int -> cur + acc }

        assertEquals(6, List.foldRight(List.of(1, 2, 3), 0, add))
    }

    @Test
    fun testLength() {
        assertEquals(3, List.length(List.of(1, 2, 3)))
    }

    @Test
    fun testFoldLeft() {
        val add = { acc: Int, cur: Int -> acc + cur }

        assertEquals(6, List.foldLeft(List.of(1, 2, 3), 0, add))
    }

    @Test
    fun testMap() {
        val toString = { a: Int -> a.toString() }

        assertEquals(
            List.of("1", "2", "3"),
            List.map(List.of(1, 2, 3), toString)
        )
    }
}
