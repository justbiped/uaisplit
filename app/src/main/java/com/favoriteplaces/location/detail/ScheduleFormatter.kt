package com.favoriteplaces.location.detail

import android.content.Context
import com.favoriteplaces.R
import com.favoriteplaces.location.detail.data.domain.ScheduleGroup

class ScheduleFormatter(private val context: Context) {


    fun format(scheduleGroup: ScheduleGroup): String {
        val shortWeekDays = scheduleGroup.shortWeekDays()

        fun isSingleDay() = shortWeekDays.size == 1
        fun isDaysInSequence() = scheduleGroup.isInSequence() && shortWeekDays.size > 3

        return when {
            isSingleDay() -> singleDayTemplate(
                shortWeekDays.first(),
                scheduleGroup.open,
                scheduleGroup.close
            )
            isDaysInSequence() -> daysInSequenceTemplate(
                shortWeekDays.first(),
                shortWeekDays.last(),
                scheduleGroup.open,
                scheduleGroup.close
            )
            else -> daysTemplate(
                getDaysFormatted(shortWeekDays),
                shortWeekDays.last(),
                scheduleGroup.open,
                scheduleGroup.close
            )
        }
    }

    private fun getDaysFormatted(shortWeekDays: List<String>) =
        shortWeekDays.joinToString().substringBeforeLast(", ")

    private fun singleDayTemplate(vararg args: String) =
        context.getString(R.string.singleDayScheduleTemplate).format(*args)

    private fun daysInSequenceTemplate(vararg args: String) =
        context.getString(R.string.daysInSequenceScheduleTemplate).format(*args)

    private fun daysTemplate(vararg args: String) =
        context.getString(R.string.daysScheduleTemplate).format(*args)
}