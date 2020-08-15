package com.favoriteplaces.location.detail.data.remote

import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.DaySchedule
import com.favoriteplaces.location.detail.data.domain.Schedules
import com.squareup.moshi.Json

data class SchedulesRemoteEntity(
    @field:Json(name = "monday") val monday: ScheduleRemoteEntity?,
    @field:Json(name = "tuesday") val tuesday: ScheduleRemoteEntity?,
    @field:Json(name = "wednesday") val wednesday: ScheduleRemoteEntity?,
    @field:Json(name = "thursday") val thursday: ScheduleRemoteEntity?,
    @field:Json(name = "friday") val friday: ScheduleRemoteEntity?,
    @field:Json(name = "saturday") val saturday: ScheduleRemoteEntity?,
    @field:Json(name = "sunday") val sunday: ScheduleRemoteEntity?
) {
    fun toDomain(): Schedules {
        val schedules = mutableListOf<DaySchedule>()

        monday?.also { schedules.add(DaySchedule(Day.MONDAY, it.open, it.close)) }
        tuesday?.also { schedules.add(DaySchedule(Day.TUESDAY, it.open, it.close)) }
        wednesday?.also { schedules.add(DaySchedule(Day.WEDNESDAY, it.open, it.close)) }
        thursday?.also { schedules.add(DaySchedule(Day.WEDNESDAY, it.open, it.close)) }
        friday?.also { schedules.add(DaySchedule(Day.WEDNESDAY, it.open, it.close)) }
        saturday?.also { schedules.add(DaySchedule(Day.WEDNESDAY, it.open, it.close)) }
        sunday?.also { schedules.add(DaySchedule(Day.WEDNESDAY, it.open, it.close)) }

        return Schedules(schedules)
    }
}