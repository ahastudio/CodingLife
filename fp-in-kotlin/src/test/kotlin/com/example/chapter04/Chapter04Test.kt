package com.example.chapter04

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class Chapter04Test {
    @Test
    fun testMap() {
        val toString = { x: Int -> x.toString() }

        assertEquals(None, None.map(toString))
        assertEquals(Some("3"), Some(3).map(toString))
    }

    @Test
    fun testFlatMap() {
        val toString = { x: Int -> Some(x.toString()) }

        assertEquals(None, None.flatMap(toString))
        assertEquals(Some("3"), Some(3).flatMap(toString))
    }

    @Test
    fun testGetOrElse() {
        assertEquals(-1, None.getOrElse { -1 })
        assertEquals(3, Some(3).getOrElse { -1 })
    }

    @Test
    fun testLift() {
        val f = { x: Int -> x.toString() }

        assertEquals(Some("3"), Option.lift(f)(Some(3)))
    }

    @Test
    fun testMap2() {
        val add = { a: Int, b: Int -> a + b }

        assertEquals(None, Option.map2(None, None, add))
        assertEquals(None, Option.map2(Some(1), None, add))
        assertEquals(None, Option.map2(None, Some(2), add))
        assertEquals(Some(3), Option.map2(Some(1), Some(2), add))
    }

    @Test
    fun testSequence() {
        assertEquals(None, Option.sequence(listOf(None, None)))
        assertEquals(None, Option.sequence(listOf(Some(1), None)))
        assertEquals(None, Option.sequence(listOf(None, Some(2))))
        assertEquals(
            Some(listOf(1, 2)),
            Option.sequence(listOf(Some(1), Some(2)))
        )
    }
}
