package com.favoriteplaces.location.detail.data.domain

data class Schedules(val schedules: List<DaySchedule>) {

    fun groupByWorkingTime(): List<ScheduleGroup> {
        return schedules
            .groupBy { "${it.open}${it.close}" }
            .map { ScheduleGroup(it.value) }
    }
}