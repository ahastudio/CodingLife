package com.example.chapter06

import kotlin.test.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

interface RNG {
    fun nextInt(): Pair<Int, RNG>
}

data class SimpleRNG(val seed: Long) : RNG {
    override fun nextInt(): Pair<Int, RNG> {
        val newSeed = (seed * 0x5DFFCE66DL + 0xBL) and 0xFFFFFFFFFFFFL
        val nextRNG = SimpleRNG(newSeed)
        val n = (newSeed ushr 16).toInt()
        return Pair(n, nextRNG)
    }
}

//typealias Rand<A> = (RNG) -> Pair<A, RNG>

data class State<S, out A>(val run: (S) -> Pair<A, S>) {
    operator fun invoke(s: S) = run(s)
}

typealias Rand<A> = State<RNG, A>

fun <A> unit(a: A): Rand<A> = Rand { rng -> Pair(a, rng) }

val intR: Rand<Int> = Rand { rng -> rng.nextInt() }

//fun nonNegativeInt(rng: RNG): Pair<Int, RNG> {
//    // 8 bits: -128 ~ +127
//    // 음수면 어떻게? -> 128을 더하자. = -128을 빼자.
//    val (x, nextRNG) = rng.nextInt()
//    val y = if (x >= 0) x else x - Int.MIN_VALUE
//    return Pair(y, nextRNG)
//}

val nonNegativeInt: Rand<Int> = map(intR) { x ->
    if (x >= 0) x
    else x - Int.MIN_VALUE
}

//fun double(rng: RNG): Pair<Double, RNG> {
//    val (x, nextRNG) = nonNegativeInt(rng)
//    val y = x.toDouble() / Int.MAX_VALUE
//    return Pair(y, nextRNG)
//}

val double: Rand<Double> = map(intR) { it.toDouble() / Int.MAX_VALUE }

fun ints(count: Int, rng: RNG): Pair<List<Int>, RNG> {
    tailrec fun go(n: Int, xs: List<Int>, rng: RNG): Pair<List<Int>, RNG> {
        if (n == 0) {
            return Pair(xs, rng)
        }
        val (x, nextRNG) = rng.nextInt()
        return go(n - 1, xs + listOf(x), nextRNG)
    }

    return go(count, listOf(), rng)
}

fun <A, B> map(s: Rand<A>, f: (A) -> B): Rand<B> = Rand { rng: RNG ->
    val (x, nextRNG) = s(rng)
    Pair(f(x), nextRNG)
}

val nonNegativeEven: Rand<Int> = map(nonNegativeInt) { it - it % 2 }

//fun nonNegativeLessThan(n: Int): Rand<Int> = { rng ->
//    val (x, nextRNG) = nonNegativeInt(rng)
//    val mod = x % n
//    if (x + (n - 1) - mod >= 0)
//        Pair(mod, nextRNG)
//    else
//        nonNegativeLessThan(n)(nextRNG)
//}

fun nonNegativeLessThan(n: Int): Rand<Int> =
    flatMap(nonNegativeInt) { x ->
        val mod = x % n
        if (x + (n - 1) - mod >= 0)
            unit(mod)
        else
            nonNegativeLessThan(n)
    }

fun <S, A, B> flatMap(f: State<S, A>, g: (A) -> State<S, B>): State<S, B> =
    State { s ->
        val (x, nextInstance) = f(s)
        g(x)(nextInstance)
    }

class Chapter06Test {
    @Test
    fun testSimpleRNG() {
        val rng = SimpleRNG(1004)
        val (x, _) = rng.nextInt()
        val (y, _) = rng.nextInt()

        assertEquals(x, y)

        val (_, rng2) = rng.nextInt()
        val (_, rng3) = rng2.nextInt()
        val (_, _) = rng3.nextInt()

        listOf(1, 2, 3)
            .fold(Pair<List<Int>, RNG>(listOf(), rng)) { acc, _ ->
                val (numbers, currentRNG) = acc
                val (n, nextRNG) = currentRNG.nextInt()
                Pair(numbers + listOf(n), nextRNG)
            }.first
    }

    @Test
    fun testNonNegativeInt() {
        val rng = SimpleRNG(1004)

        val (x, _) = nonNegativeInt(rng)

        assertTrue(x >= 0)
    }

    @Test
    fun testDouble() {
        val rng = SimpleRNG(1004)

        val (x, _) = double(rng)

        assertTrue(x >= 0)
        assertTrue(x <= 1)
    }

    @Test
    fun testInts() {
        val rng = SimpleRNG(1004)

        val (xs, _) = ints(4, rng)

        assertEquals(4, xs.size)
    }

    @Test
    fun testNonNegativeEven() {
        val rng = SimpleRNG(1004)

        val (x, _) = nonNegativeEven(rng)

        assertTrue(x >= 0)
        assertTrue(x % 2 == 0)
    }

    @Test
    fun testNonNegativeLessThan() {
        val rng = SimpleRNG(1004)

        val (x, _) = nonNegativeLessThan(10)(rng)

        assertTrue(x >= 0)
        assertTrue(x < 10)
    }
}
