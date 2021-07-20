package com.favoriteplaces.location.detail.data.domain

import java.text.DateFormatSymbols
import java.util.*

data class ScheduleGroup(private val schedules: List<DaySchedule>) {
    val open = schedules.first().open
    val close = schedules.first().close

    val shortWeekDays: List<String>
    val isInSequence: Boolean

    init {
        shortWeekDays = createShortWeekDays()
        isInSequence = isWeekDaysInSequence()
    }

    private fun createShortWeekDays(): List<String> {
        return schedules.map { schedule ->
            DateFormatSymbols(Locale.getDefault()).shortWeekdays[schedule.day.index]
        }
    }

    private fun isWeekDaysInSequence(): Boolean {
        var isSequence = true
        var nextDay = schedules.first().day.ordinal

        schedules.forEach {
            isSequence = isSequence && it.day.ordinal == nextDay++
        }

        return isSequence
    }
}

sealed class WeekDays {
    data class Single(
        val day: String,
        val open: String,
        val close: String
    ) : WeekDays()

    data class Sequence(
        val firstDay: String,
        val lastDay: String,
        val open: String,
        val close: String
    ) : WeekDays()

    data class Mixed(
        private val shortWeekDays: List<String>,
        val lastDay: String,
        val open: String,
        val close: String
    ) {

        val days: String = shortWeekDays.joinToString().substringBeforeLast(", ")
    }

}