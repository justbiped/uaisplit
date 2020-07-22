package com.favoriteplaces.location.detail.data.domain

data class Schedules(val schedules: List<Schedule>) {

    fun groupByWorkingTime(): List<ScheduleGroup> {
        return schedules
            .groupBy { "${it.open}${it.close}" }
            .filter { it.key.isNotBlank() }
            .map {
                ScheduleGroup(
                    it.value
                )
            }
    }
}