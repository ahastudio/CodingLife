package com.example.chapter05

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
