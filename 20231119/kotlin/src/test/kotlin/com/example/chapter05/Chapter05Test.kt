package com.example.chapter05

import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import org.junit.jupiter.api.Test

sealed class Stream<out A> {
    companion object {
        fun <A> of(vararg xs: A): Stream<A> = when {
            xs.isEmpty() -> Empty
            else -> Cons(
                { xs[0] },
                of(*xs.sliceArray(1 until xs.size))
            )
        }
    }
}

object Empty : Stream<Nothing>()

data class Cons<out A>(
    val head: () -> A,
    val tail: Stream<A>
) : Stream<A>()

fun <A> Stream<A>.toList(): List<A> = when (this) {
    is Empty -> listOf()
    is Cons -> listOf(this.head()) + this.tail.toList()
}

fun <A> Stream<A>.drop(n: Int): Stream<A> = when (this) {
    is Empty -> Empty
    is Cons ->
        if (n == 0) this
        else this.tail.drop(n - 1)
}

fun <A, B> Stream<A>.foldRight(z: B, f: (A, B) -> B): B = when (this) {
    is Empty -> z
    is Cons -> f(this.head(), this.tail.foldRight(z, f))
}

//fun <A> Stream<A>.exists(p: (A) -> Boolean): Boolean = when (this) {
//    is Empty -> false
//    is Cons -> p(this.head()) || this.tail.exists(p)
//}

fun <A> Stream<A>.exists(p: (A) -> Boolean): Boolean =
    this.foldRight(false) { a: A, b: Boolean -> p(a) || b }

class Chapter05Test {
    @Test
    fun testToList() {
        assertEquals(listOf(1, 2, 3), Stream.of(1, 2, 3).toList())
    }

    @Test
    fun testDrop() {
        assertEquals(listOf(2, 3), Stream.of(1, 2, 3).drop(1).toList())
        assertEquals(listOf(3), Stream.of(1, 2, 3).drop(2).toList())
        assertEquals(listOf(), Stream.of(1, 2, 3).drop(3).toList())
    }

    @Test
    fun testExists() {
        assertTrue(Stream.of(1, 2, 3).exists { x -> x == 1 })
        assertTrue(Stream.of(1, 2, 3).exists { x -> x == 2 })
        assertTrue(Stream.of(1, 2, 3).exists { x -> x == 3 })
        assertFalse(Stream.of(1, 2, 3).exists { x -> x == 4 })
    }

    @Test
    fun testFoldRight() {
        assertEquals(
            6,
            Stream.of(1, 2, 3).foldRight(0) { cur, acc -> acc + cur }
        )
    }
}
