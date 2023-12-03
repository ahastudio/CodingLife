package com.example.chapter04

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
            else -> map2(
                xs.first(),
                sequence(xs.drop(1))
            ) { a, b -> listOf(a) + b }
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
