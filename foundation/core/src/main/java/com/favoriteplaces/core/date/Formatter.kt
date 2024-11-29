package com.favoriteplaces.core.date

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import timber.log.Timber

private val UTC_ID = ZoneId.of("UTC")
const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"

fun Long.formatAsDate(pattern: String = ISO_8601): String {
    val instant = Instant.ofEpochMilli(this)
    val localDate = ZonedDateTime
        .ofInstant(instant, UTC_ID)
        .toLocalDate()
        .atStartOfDay()

    return DateTimeFormatter.ofPattern(pattern).format(localDate)
}

fun String.asEpoch(pattern: String = ISO_8601): Long {
    val formatter = DateTimeFormatter.ofPattern(pattern).withZone(UTC_ID)
    return try {
        LocalDate.from(formatter.parse(this))
            .atStartOfDay()
            .atZone(UTC_ID)
            .toInstant()
            .toEpochMilli()
    } catch (error: Throwable) {
        Timber.e(error)
        currentEpoch()
    }
}

fun currentEpoch(): Long = ZonedDateTime
    .now()
    .toLocalDate()
    .atStartOfDay(UTC_ID)
    .toInstant()
    .toEpochMilli()