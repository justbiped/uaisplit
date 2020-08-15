package com.favoriteplaces.location.detail.data.domain

import java.text.DateFormatSymbols
import java.util.*

data class ScheduleGroup(private val schedules: List<DaySchedule>) {
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