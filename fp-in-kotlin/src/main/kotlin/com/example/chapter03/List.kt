package com.example.chapter03

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
