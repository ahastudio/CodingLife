package com.example.chapter10

interface Kind<out F, out A>

interface Foldable<F> {
    fun <A, B> foldRight(fa: Kind<F, A>, z: B, f: (A, B) -> B): B {
        val m = endofunction<B>()
        val composed = foldMap(fa, m) { a -> { b -> f(a, b) } }
        return composed(z)
    }

    fun <A, B> foldLeft(fa: Kind<F, A>, z: B, f: (B, A) -> B): B {
        val m = endofunction<B>()
        val composed = foldMap(fa, m) { a -> { b -> f(b, a) } }
        return composed(z)
    }

    fun <A, B> foldMap(fa: Kind<F, A>, m: Monoid<B>, f: (A) -> B): B =
        foldRight(fa, m.nil) { cur, acc -> m.combine(f(cur), acc) }

    fun <A> concatenate(fa: Kind<F, A>, m: Monoid<A>): A =
        foldLeft(fa, m.nil, m::combine)
}
