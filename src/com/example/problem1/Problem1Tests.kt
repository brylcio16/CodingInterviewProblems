package com.example.problem1

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class Problem1Tests {
    @Test
    fun rob_VectorNull_Return0() {
        val v = null
        val result = Problem1.rob(v)
        assertEquals(0, result)
    }

    @Test
    fun rob_VectorEmpty_Return0() {
        val v = Vector<Int>()
        val result = Problem1.rob(v)
        assertEquals(0, result)
    }

    @Test
    fun rob_VectorWithOneItem_ReturnFirstItem() {
        val v = Vector<Int>(listOf(2))
        val result = Problem1.rob(v)
        assertEquals(2, result)
    }

    @Test
    fun rob_VectorWithTwoItems_ReturnMaxOfTwo() {
        var v = Vector<Int>(listOf(2, 3))
        var result = Problem1.rob(v)
        assertEquals(3, result)

        v = Vector(listOf(3, 2))
        result = Problem1.rob(v)
        assertEquals(3, result)
    }

    @Test
    fun rob_CorrectAnswer() {
        val v = Vector<Int>(listOf(1, 2, 3, 1))
        val result = Problem1.rob(v)
        assertEquals(4, result)
    }
}