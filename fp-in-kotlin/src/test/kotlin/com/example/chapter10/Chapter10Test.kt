package com.example.chapter10

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

fun <A> bag(xs: List<A>): Map<A, Int> {
    val m = mapMergeMonoid<A, Int>(intAddition())
    return ListFoldable.foldMap(xs, m) { x: A -> mapOf(x to 1) }
}

class Chapter10Test {
    @Test
    fun testIntAddition() {
        val m = intAddition()

        assertEquals(
            m.combine(m.combine(1, 2), 3),
            m.combine(1, m.combine(2, 3)),
        )

        assertEquals(3, m.combine(3, m.nil))
        assertEquals(3, m.combine(m.nil, 3))
    }

    @Test
    fun testIntMultiplication() {
        val m = intMultiplication()

        assertEquals(
            m.combine(m.combine(4, 2), 3),
            m.combine(4, m.combine(2, 3)),
        )

        assertEquals(3, m.combine(3, m.nil))
        assertEquals(3, m.combine(m.nil, 3))
    }

    @Test
    fun testMonoid() {
        listOf(intAddition(), intMultiplication()).forEach { m ->
            assertEquals(
                m.combine(m.combine(1, 2), 3),
                m.combine(1, m.combine(2, 3)),
            )

            assertEquals(3, m.combine(3, m.nil))
            assertEquals(3, m.combine(m.nil, 3))
        }
    }

    @Test
    fun testOptionMonoid() {
        val m = optionMonoid<Int>()

        // 항등원

        assertEquals(
            Some(1),
            m.combine(m.nil, Some(1))
        )

        assertEquals(
            Some(1),
            m.combine(Some(1), m.nil)
        )

        // 결합법칙

        assertEquals(
            Some(1),
            m.combine(m.combine(Some(1), Some(2)), Some(3))
        )

        assertEquals(
            Some(1),
            m.combine(Some(1), m.combine(Some(2), Some(3)))
        )
    }

    @Test
    fun testEndofunction() {
        val m = endofunction<Int>()

        val f = { x: Int -> x * 2 }

        assertEquals(f(3), m.combine(m.nil, f)(3))
        assertEquals(f(3), m.combine(f, m.nil)(3))
    }

    @Test
    fun testListFoldable() {
        assertEquals(
            6,
            ListFoldable.foldRight(List.of(1, 2, 3), 0) { a, b -> a + b }
        )

        assertEquals(
            "123",
            ListFoldable.foldRight(
                List.of(1, 2, 3),
                ""
            ) { a, b -> a.toString() + b }
        )

        assertEquals(
            6,
            ListFoldable.foldMap(List.of(1, 2, 3), intAddition()) { it }
        )

        assertEquals(
            "123",
            ListFoldable.foldMap(
                List.of(1, 2, 3),
                stringConcat()
            ) { it.toString() }
        )

        assertEquals(
            6,
            ListFoldable.concatenate(List.of(1, 2, 3), intAddition())
        )
    }

    @Test
    fun testOptionFoldable() {
        val m = optionMonoid<Int>()

        assertEquals(
            Some(1),
            OptionFolderable.foldMap(Some(1), m) { Some(it) }
        )

        assertEquals(
            None,
            OptionFolderable.foldMap(None, m) { x: Int -> Some(x) }
        )
    }

    @Test
    fun testMapMergeMonoid() {
        val m = mapMergeMonoid<String, Int>(intAddition())

        assertEquals(
            mapOf("a" to 3),
            m.combine(mapOf("a" to 1), mapOf("a" to 2))
        )
    }

    @Test
    fun testBag() {
        assertEquals(
            mapOf("a" to 2, "rose" to 2, "is" to 1),
            bag(List.of("a", "rose", "is", "a", "rose"))
        )
    }
}
