package com.example.chapter06

data class State<S, out A>(val run: (S) -> Pair<A, S>) {
    operator fun invoke(s: S) = run(s)
}

typealias Rand<A> = State<RNG, A>

fun <A> unit(a: A): Rand<A> = Rand { rng -> Pair(a, rng) }

val intR: Rand<Int> = Rand { rng -> rng.nextInt() }

fun <A, B> map(s: Rand<A>, f: (A) -> B): Rand<B> = Rand { rng: RNG ->
    val (x, nextRNG) = s(rng)
    Pair(f(x), nextRNG)
}

fun <S, A, B> flatMap(f: State<S, A>, g: (A) -> State<S, B>): State<S, B> =
    State { s ->
        val (x, nextInstance) = f(s)
        g(x)(nextInstance)
    }
