package com.example.chapter10

object ListFoldable : Foldable<ForList> {
//    override fun <A, B> foldMap(
//        fa: Kind<ForList, A>,
//        m: Monoid<B>,
//        f: (A) -> B
//    ): B = when (val xs = fa.fix()) {
//        is Nil -> m.nil
//        is Cons -> m.combine(f(xs.head), foldMap(xs.tail, m, f))
//    }

    override fun <A, B> foldMap(
        fa: Kind<ForList, A>,
        m: Monoid<B>,
        f: (A) -> B
    ): B = fa.fix().foldMap(m, f)

//    override fun <A, B> foldMap(
//        fa: Kind<ForList, A>,
//        m: Monoid<B>,
//        f: (A) -> B
//    ): B = fa.fix().foldRight(m.nil) { cur, acc -> m.combine(f(cur), acc) }
}
