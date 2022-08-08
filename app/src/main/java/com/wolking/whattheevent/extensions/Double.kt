package com.wolking.whattheevent.extensions

import java.text.DecimalFormat

fun Double.toMoneyReal(): String {
    val formatter = DecimalFormat("###,###,##0,00")
    return "R$ ${formatter.format(this)}"
}