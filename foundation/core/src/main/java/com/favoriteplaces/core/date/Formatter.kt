package com.favoriteplaces.core.date

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale
import timber.log.Timber

fun Long.formatAsDate(pattern: String = "yyyy-MM-dd"): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(Date(this))
}

fun String.asTime(pattern: String = "yyyy-MM-dd"): Instant {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return try {
        formatter.parse(this)?.toInstant() ?: throw ParseException("Parse result was null", 0)
    } catch (error: Throwable) {
        Timber.e(error)
        Instant.now()
    }
}