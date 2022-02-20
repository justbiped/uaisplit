package com.favoriteplaces.location.detail.data.remote

import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.DaySchedule
import com.favoriteplaces.location.detail.data.domain.Schedule
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchedulesApiModel(
    @SerialName("monday") val monday: ScheduleRemoteEntity? = null,
    @SerialName("tuesday") val tuesday: ScheduleRemoteEntity? = null,
    @SerialName("wednesday") val wednesday: ScheduleRemoteEntity? = null,
    @SerialName("thursday") val thursday: ScheduleRemoteEntity? = null,
    @SerialName("friday") val friday: ScheduleRemoteEntity? = null,
    @SerialName("saturday") val saturday: ScheduleRemoteEntity? = null,
    @SerialName("sunday") val sunday: ScheduleRemoteEntity? = null
) {
    fun toDomain(): Schedule {
        val schedules = mutableListOf<DaySchedule>()

        monday?.also { schedules.add(DaySchedule(Day.MONDAY, it.open, it.close)) }
        tuesday?.also { schedules.add(DaySchedule(Day.TUESDAY, it.open, it.close)) }
        wednesday?.also { schedules.add(DaySchedule(Day.WEDNESDAY, it.open, it.close)) }
        thursday?.also { schedules.add(DaySchedule(Day.THURSDAY, it.open, it.close)) }
        friday?.also { schedules.add(DaySchedule(Day.FRIDAY, it.open, it.close)) }
        saturday?.also { schedules.add(DaySchedule(Day.SATURDAY, it.open, it.close)) }
        sunday?.also { schedules.add(DaySchedule(Day.SUNDAY, it.open, it.close)) }

        return Schedule(schedules)
    }
}

