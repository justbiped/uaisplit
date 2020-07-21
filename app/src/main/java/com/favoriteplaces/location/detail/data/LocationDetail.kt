package com.favoriteplaces.location.detail.data

import java.text.DateFormatSymbols
import java.util.*

data class LocationDetail(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
    val about: String,
    val phone: String,
    val address: String,
    val schedules: Schedules
)

data class Schedules(val schedules: List<Schedule>) {

    fun groupByWorkingTime(): List<ScheduleGroup> {
        return schedules
            .groupBy { "${it.open}${it.close}" }
            .filter { it.key.isNotBlank() }
            .map { ScheduleGroup(it.value) }
    }
}

data class ScheduleGroup(private val schedules: List<Schedule>) {
    val open = schedules.first().open
    val close = schedules.first().close

    fun isInSequence(): Boolean {
        var isSequence = true
        var nextDay = schedules.first().day.ordinal

        schedules.forEach {
            isSequence = isSequence && it.day.ordinal == nextDay++
        }

        return isSequence
    }

    fun shortWeekDays(): List<String> {
        return schedules.map { schedule ->
            DateFormatSymbols(Locale.getDefault()).shortWeekdays[schedule.day.index]
        }
    }
}

data class Schedule(
    val day: Day,
    val open: String,
    val close: String
)

enum class Day(val index: Int) {
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7),
    SUNDAY(1)
}