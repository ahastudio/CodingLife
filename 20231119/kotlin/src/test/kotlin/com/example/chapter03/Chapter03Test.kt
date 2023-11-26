package com.example.chapter03

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

sealed class List<out A> {
    companion object {
        fun <A> of(vararg xs: A): List<A> = when {
            xs.isEmpty() -> Nil
            else -> Cons(
                xs[0],
                of(*xs.sliceArray(1 until xs.size))
            )
        }

        fun sum(xs: List<Int>): Int = when (xs) {
            is Nil -> 0
            is Cons -> xs.head + sum(xs.tail)
        }

        fun product(xs: List<Int>): Int = when (xs) {
            is Nil -> 1
            is Cons -> xs.head * product(xs.tail)
        }

        fun <A> tail(xs: List<A>): List<A> = when (xs) {
            is Nil -> Nil
            is Cons -> xs.tail
        }

        fun <A> setHead(xs: List<A>, x: A): List<A> =
            Cons(x, tail(xs))

        fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B): B = when (xs) {
            is Nil -> z
            is Cons -> f(xs.head, foldRight(xs.tail, z, f))
        }

        fun <A> length(xs: List<A>): Int =
            foldRight(xs, 0) { _: A, acc: Int -> acc + 1 }

        fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B = when (xs) {
            is Nil -> z
            is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
        }

//        fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> = when (xs) {
//            is Nil -> Nil
//            is Cons -> Cons(f(xs.head), map(xs.tail, f))
//        }

        fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> =
            foldRight(xs, Nil) { cur: A, acc: List<B> -> Cons(f(cur), acc) }
    }
}

object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()

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
