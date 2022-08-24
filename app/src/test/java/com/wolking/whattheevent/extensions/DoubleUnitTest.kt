package com.wolking.whattheevent.extensions

import org.junit.Test

import org.junit.Assert.*

class DoubleUnitTest {
    @Test
    fun toMoneyRealTest() {
        val price = 29.99
        assert(price.toMoneyReal().contains("R$"))
        assert(price.toMoneyReal().contains("29,99"))
    }
}