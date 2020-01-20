package com.tr1984.mvvmsample.extensions

import android.content.res.Resources
import java.text.NumberFormat

fun Int.toPx(): Int {
    val metrics = Resources.getSystem().displayMetrics
    return (this * metrics.density + 0.5f).toInt()
}

fun Int.withComma() = NumberFormat.getInstance().format(this)