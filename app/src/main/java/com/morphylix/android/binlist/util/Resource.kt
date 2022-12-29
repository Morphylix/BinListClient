package com.morphylix.android.binlist.util

import android.content.Context
import com.morphylix.android.binlist.R
import retrofit2.HttpException
import java.net.UnknownHostException

fun handleException(exception: Exception, context: Context): String {
    val error = when(exception) {
        is HttpException -> {
            when (exception.code()) {
                400 -> context.getString(R.string.no_connection)
                404 -> context.getString(R.string.card_not_found)
                else -> exception.response().toString()
            }
        }
        is UnknownHostException -> R.string.no_connection
        else -> exception.cause?.message.toString()
    }
    return context.getString(R.string.error, error)
}