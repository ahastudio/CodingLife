package com.example.chapter10

class ForList private constructor() {
    companion object
}

typealias ListOf<A> = Kind<ForList, A>

fun <A> ListOf<A>.fix() = this as List<A>

// sealed class List<out A> : Kind<ForList, A>
sealed class List<out A> : ListOf<A> {
    companion object {
        fun <A> of(vararg xs: A): List<A> = when {
            xs.isEmpty() -> Nil
            else -> Cons(
                xs[0],
                of(*xs.sliceArray(1 until xs.size))
            )
        }
    }
}

object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()

fun <A, B> List<A>.foldRight(z: B, f: (A, B) -> B): B = when (this) {
    is Nil -> z
    is Cons -> f(this.head, this.tail.foldRight(z, f))
}
