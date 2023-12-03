package com.example.chapter06

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

//fun <A> unit(a: A): Rand<A> = { rng -> Pair(a, rng) }

//val intR: Rand<Int> = { rng -> rng.nextInt() }

//fun <A, B> map(s: Rand<A>, f: (A) -> B): Rand<B> = { rng: RNG ->
//    val (x, nextRNG) = s(rng)
//    Pair(f(x), nextRNG)
//}

//fun <A, B> flatMap(f: Rand<A>, g: (A) -> Rand<B>): Rand<B> = { s ->
//    val (x, nextInstance) = f(s)
//    g(x)(nextInstance)
//}

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
