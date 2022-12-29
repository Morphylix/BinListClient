package com.morphylix.android.binlist.util

import android.text.Editable
import android.text.SpannableStringBuilder
import android.util.Log

private const val TAG = "Extensions"

fun String.splitEveryFourDigits(): String {
    val res = StringBuilder()
    var i = 0
    for (char in this.filterNot { it.isWhitespace() }) {
        Log.i(TAG, "$char")
        if (i % 4 == 0 && i != 0) {
            res.append(' ')
        }
        res.append(char)
        i++
    }
    return res.toString()
}

fun Boolean.convertToYesOrNo(): String {
    return if (this) "Yes" else "No"
}