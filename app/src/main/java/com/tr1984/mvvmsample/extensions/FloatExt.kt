package com.tr1984.mvvmsample.extensions

import android.content.res.Resources

fun Float.toPx(): Float {
    val metrics = Resources.getSystem().displayMetrics
    return (this * metrics.density + 0.5f)
}