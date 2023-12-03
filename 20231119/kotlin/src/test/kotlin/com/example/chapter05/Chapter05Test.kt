package com.example.chapter05

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test
import com.example.chapter04.None
import com.example.chapter04.Option
import com.example.chapter04.Some

sealed class Stream<out A> {
    companion object {
        fun <A> cons(head: () -> A, tail: () -> Stream<A>): Stream<A> {
            val h: A by lazy(head)
            val t: Stream<A> by lazy(tail)
            return Cons({ h }, { t })
        }

        fun <A> of(vararg xs: A): Stream<A> = when {
            xs.isEmpty() -> Empty
            else -> Cons(
                { xs[0] },
                { of(*xs.sliceArray(1 until xs.size)) }
            )
        }
    }
}

object Empty : Stream<Nothing>()

data class Cons<out A>(
    val head: () -> A,
    val tail: () -> Stream<A>
) : Stream<A>()

fun <A> Stream<A>.toList(): List<A> = when (this) {
    is Empty -> listOf()
    is Cons -> listOf(this.head()) + this.tail().toList()
}

fun <A> Stream<A>.take(n: Int): Stream<A> = when (this) {
    is Empty -> Empty
    is Cons ->
        if (n == 0) Empty
        else Cons(this.head) { this.tail().take(n - 1) }
}

fun <A> Stream<A>.drop(n: Int): Stream<A> = when (this) {
    is Empty -> Empty
    is Cons ->
        if (n == 0) this
        else this.tail().drop(n - 1)
}

fun <A, B> Stream<A>.foldRight(z: () -> B, f: (A, () -> B) -> B): B =
    when (this) {
        is Empty -> z()
        is Cons -> f(this.head()) { this.tail().foldRight(z, f) }
    }

//fun <A> Stream<A>.exists(p: (A) -> Boolean): Boolean = when (this) {
//    is Empty -> false
//    is Cons -> p(this.head()) || this.tail().exists(p)
//}

fun <A> Stream<A>.exists(p: (A) -> Boolean): Boolean =
    this.foldRight({ false }) { a: A, b: () -> Boolean -> p(a) || b() }

fun <A> Stream<A>.forAll(p: (A) -> Boolean): Boolean =
    this.foldRight({ true }) { a: A, b: () -> Boolean -> p(a) && b() }

fun <A, B> Stream<A>.map(f: (A) -> B): Stream<B> =
    this.foldRight({ Empty }) { cur: A, acc: () -> Stream<B> ->
        Cons({ f(cur) }, acc)
    }

fun <A> Stream<A>.filter(p: (A) -> Boolean): Stream<A> =
    this.foldRight({ Empty }) { cur: A, acc: () -> Stream<A> ->
        if (p(cur)) Cons({ cur }, acc)
        else acc()
    }

fun ones(): Stream<Int> = Cons({ 1 }, { ones() })

fun <A, S> unfold(z: S, f: (S) -> Option<Pair<A, S>>): Stream<A> =
    when (val x = f(z)) {
        is None -> Empty
        is Some -> {
            val (a, b) = x.get
            Cons({ a }, { unfold(b, f) })
        }
    }

// 패턴 매칭이 된다면...
// match (f(z)) {
//     None -> Empty
//     Some(a, b) -> Cons({ a }, { unfold(b, f) })
// }

class Chapter05Test {
    @Test
    fun testToList() {
        assertEquals(listOf(1, 2, 3), Stream.of(1, 2, 3).toList())
    }

    @Test
    fun testTake() {
        assertEquals(listOf(), Stream.of(1, 2, 3).take(0).toList())
        assertEquals(listOf(1), Stream.of(1, 2, 3).take(1).toList())
        assertEquals(listOf(1, 2), Stream.of(1, 2, 3).take(2).toList())
        assertEquals(listOf(1, 2, 3), Stream.of(1, 2, 3).take(3).toList())
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
            Stream.of(1, 2, 3).foldRight({ 0 }) { cur, acc -> cur + acc() }
        )
    }

    @Test
    fun testForAll() {
        assertTrue(Stream.of(1, 2, 3).forAll { x -> x > 0 })
        assertFalse(Stream.of(1, 2, 3).forAll { x -> x > 1 })
        assertFalse(Stream.of(1, 2, 3).forAll { x -> x % 2 == 1 })
    }

    @Test
    fun testMap() {
        assertEquals(
            listOf(2, 4, 6),
            Stream.of(1, 2, 3).map { x -> x * 2 }.toList()
        )
    }

    @Test
    fun testFilter() {
        assertEquals(
            listOf(2, 4),
            Stream.of(1, 2, 3, 4).filter { x -> x % 2 == 0 }.toList()
        )
    }

    @Test
    fun testSample() {
        assertEquals(
            listOf(36, 42),
            listOf(1, 2, 3, 4)
                .map { it + 10 } // --> 4개 (중간 과정)
                .filter { it % 2 == 0 } // --> 2개 (중간 과정)
                .map { it * 3 } // --> 2개
        )

        assertEquals(
            listOf(36, 42),
            Stream.of(1, 2, 3, 4)
                .map { it + 10 }
                .filter { it % 2 == 0 }
                .map { it * 3 } // --> 여기까지는 전부 지연. 평가를 하지 않음.
                .toList() // --> 평가 시작.
        )

//        x -> f(g(h(x)))
//        x -> (f.g.h)(x)

        Stream.of(1, 2, 3, 4)
            .map { it + 10 }
            .filter { it % 2 == 0 }

        Stream.cons(
            { 1 },
            { Stream.of(2, 3, 4) }
        )
            .map { it + 10 }
            .filter { it % 2 == 0 }

        Stream.cons(
            { 1 + 10 },
            { Stream.of(2, 3, 4).map { it + 10 } }
        )
            .filter { it % 2 == 0 }

        Stream.of(2, 3, 4).map { it + 10 }
            .filter { it % 2 == 0 }
    }

    @Test
    fun testOnes() {
        assertEquals(listOf(1, 1, 1, 1), ones().take(4).toList())
    }

    @Test
    fun testUnfold() {
        assertEquals(
            listOf(0, 2, 4),
            unfold(0) { x: Int -> Some(Pair(x * 2, x + 1)) }.take(3).toList()
        )
    }
}
