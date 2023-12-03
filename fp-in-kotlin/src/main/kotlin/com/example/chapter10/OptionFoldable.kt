package com.example.chapter10

object OptionFoldable : Foldable<ForOption> {
//    override fun <A, B> foldMap(
//        fa: Kind<ForOption, A>,
//        m: Monoid<B>,
//        f: (A) -> B
//    ): B = when (val x = fa.fix()) {
//        is None -> m.nil
//        is Some -> f(x.get)
//    }

    override fun <A, B> foldMap(
        fa: Kind<ForOption, A>,
        m: Monoid<B>,
        f: (A) -> B
    ): B = fa.fix().foldMap(m, f)
}
