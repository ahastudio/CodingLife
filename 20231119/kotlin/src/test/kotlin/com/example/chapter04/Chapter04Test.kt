package com.example.chapter04

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

// 확장 프로퍼티
val <T> List<T>.head: T
    get() = first()
val <T> List<T>.tail: List<T>
    get() = drop(1)

sealed class Option<out A> {
    companion object {
        fun <A, B> lift(f: (A) -> B): (Option<A>) -> Option<B> =
            { a: Option<A> -> a.map(f) }

        fun <A, B, C> map2(
            a: Option<A>,
            b: Option<B>,
            f: (A, B) -> C
        ): Option<C> =
            a.flatMap { x ->
                b.map { y ->
                    f(x, y)
                }
            }

        fun <A> sequence(xs: List<Option<A>>): Option<List<A>> = when {
            xs.isEmpty() -> Some(listOf())
            else -> map2(xs.head, sequence(xs.tail)) { a, b ->
                listOf(a) + b
            }
        }
    }
}

object None : Option<Nothing>()

data class Some<out A>(val get: A) : Option<A>()

fun <A, B> Option<A>.map(f: (A) -> B): Option<B> = when (this) {
    is None -> None
    is Some -> Some(f(this.get))
}

fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> = when (this) {
    is None -> None
    is Some -> f(this.get)
}

fun <A> Option<A>.getOrElse(f: () -> A): A = when (this) {
    is None -> f()
    is Some -> this.get
}

class Chapter04Test {
    @Test
    fun testMap() {
        val toString = { x: Int -> x.toString() }

        assertEquals(None, None.map(toString))
        assertEquals(Some("3"), Some(3).map(toString))
    }

    @Test
    fun testFlatMap() {
        val toString = { x: Int -> Some(x.toString()) }

        assertEquals(None, None.flatMap(toString))
        assertEquals(Some("3"), Some(3).flatMap(toString))
    }

    @Test
    fun testGetOrElse() {
        assertEquals(-1, None.getOrElse { -1 })
        assertEquals(3, Some(3).getOrElse { -1 })
    }

    @Test
    fun testLift() {
        val f = { x: Int -> x.toString() }

        assertEquals(Some("3"), Option.lift(f)(Some(3)))
    }

    @Test
    fun testMap2() {
        val add = { a: Int, b: Int -> a + b }

        assertEquals(None, Option.map2(None, None, add))
        assertEquals(None, Option.map2(Some(1), None, add))
        assertEquals(None, Option.map2(None, Some(2), add))
        assertEquals(Some(3), Option.map2(Some(1), Some(2), add))
    }

    @Test
    fun testSequence() {
        assertEquals(None, Option.sequence(listOf(None, None)))
        assertEquals(None, Option.sequence(listOf(Some(1), None)))
        assertEquals(None, Option.sequence(listOf(None, Some(2))))
        assertEquals(
            Some(listOf(1, 2)),
            Option.sequence(listOf(Some(1), Some(2)))
        )
    }
}
