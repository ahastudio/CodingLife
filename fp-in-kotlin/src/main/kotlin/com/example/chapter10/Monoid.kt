package com.example.chapter10

interface Monoid<A> {
    fun combine(x: A, y: A): A

    val nil: A
}

fun intAddition(): Monoid<Int> = object : Monoid<Int> {
    override fun combine(x: Int, y: Int): Int = x + y

    override val nil: Int = 0
}

fun intMultiplication(): Monoid<Int> = object : Monoid<Int> {
    override fun combine(x: Int, y: Int): Int = x * y

    override val nil: Int = 1
}

fun stringConcat(): Monoid<String> = object : Monoid<String> {
    override fun combine(x: String, y: String): String = x + y

    override val nil: String = ""
}

fun <A> optionMonoid(): Monoid<Option<A>> = object : Monoid<Option<A>> {
    // Option의 or 연산 (항등원: None)
    override fun combine(x: Option<A>, y: Option<A>): Option<A> = when (x) {
        is None -> y
        is Some -> x
    }

    override val nil: Option<A> = None
}

fun <A> endofunction(): Monoid<(A) -> A> = object : Monoid<(A) -> A> {
    override fun combine(x: (A) -> A, y: (A) -> A): (A) -> A =
        { a -> x(y(a)) }

    override val nil: (A) -> A = { a -> a }
}

fun <K, V> mapMergeMonoid(m: Monoid<V>): Monoid<Map<K, V>> =
    object : Monoid<Map<K, V>> {
        override fun combine(x: Map<K, V>, y: Map<K, V>): Map<K, V> =
            (x.keys + y.keys).fold(mapOf()) { acc: Map<K, V>, key: K ->
                acc + mapOf(
                    key to m.combine(
                        x.getOrElse(key) { m.nil },
                        y.getOrElse(key) { m.nil },
                    )
                )
            }

        override val nil: Map<K, V> = mapOf()
    }
