package com.example.chapter10

class ForOption private constructor() {
    companion object
}

typealias OptionOf<A> = Kind<ForOption, A>

fun <A> OptionOf<A>.fix() = this as Option<A>

// sealed class Option<out A> : Kind<ForOption, A>
 sealed class Option<out A> : OptionOf<A>

object None : Option<Nothing>()

data class Some<out A>(val get: A) : Option<A>()

fun <A, B> Option<A>.foldMap(m: Monoid<B>, f: (A) -> B): B = when (this) {
    is None -> m.nil
    is Some -> f(this.get)
}
