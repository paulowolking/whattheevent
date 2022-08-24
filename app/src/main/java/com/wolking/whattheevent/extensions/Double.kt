package com.wolking.whattheevent.extensions

import java.text.NumberFormat
import java.util.*

fun Double.toMoneyReal(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return numberFormat.format(this)
}