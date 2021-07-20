package com.favoriteplaces.location.detail.data.domain

class Schedule(private val schedules: List<DaySchedule>) {

    val workingHourGroups = groupByWorkingTime()

    private fun groupByWorkingTime(): List<Group> {
        return schedules
            .groupBy { "${it.open},${it.close}" }
            .map {
                val (open, close) = it.key.split(",")
                Group(open, close, it.value)
            }
    }

    data class Group(
        val open: String,
        val close: String,
        val days: List<DaySchedule>
    ) {
        val firstDay = days.first().day
        val lastDay = days.last().day

        val isInSequence: Boolean = isWeekDaysInSequence()

        private fun isWeekDaysInSequence(): Boolean {
            var isSequence = true
            var nextDay = days.first().day.ordinal

            days.forEach {
                isSequence = isSequence && it.day.ordinal == nextDay++
            }

            return isSequence
        }
    }
}