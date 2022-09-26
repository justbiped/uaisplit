package com.favoriteplaces.location.detail.ui

import android.content.Context
import com.favoriteplaces.location.R
import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.Schedule
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.DateFormatSymbols
import java.util.Locale
import javax.inject.Inject

internal class ScheduleFormatter @Inject constructor(@ApplicationContext private val context: Context) {

    fun format(schedule: Schedule): String {
        val stringBuilder = StringBuilder()

        schedule.workingHourGroups.forEach { stringBuilder.append("${formatGroup(it)}\n") }
        return stringBuilder.toString()
    }

    private fun formatGroup(group: Schedule.Group): String {
        fun isSingleDay() = group.days.size == 1
        fun isDaysInSequence() = group.isInSequence && group.days.size > 3

        return when {
            isSingleDay() -> singleDayTemplate(
                formatDay(group.firstDay),
                group.open,
                group.close
            )
            isDaysInSequence() -> daysInSequenceTemplate(
                formatDay(group.firstDay),
                formatDay(group.lastDay),
                group.open,
                group.close
            )
            else -> daysTemplate(
                getDaysFormatted(group),
                formatDay(group.lastDay),
                group.open,
                group.close
            )
        }
    }

    private fun formatDay(day: Day): String {
        return DateFormatSymbols(Locale.getDefault()).shortWeekdays[day.index]
    }

    private fun getDaysFormatted(group: Schedule.Group) =
        group.days.joinToString { formatDay(it.day) }.substringBeforeLast(", ")

    private fun singleDayTemplate(vararg args: String) =
        context.getString(R.string.single_day_schedule_template).format(*args)

    private fun daysInSequenceTemplate(vararg args: String) =
        context.getString(R.string.daysIn_sequence_schedule_template).format(*args)

    private fun daysTemplate(vararg args: String) =
        context.getString(R.string.days_schedule_template).format(*args)
}