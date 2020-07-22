package com.favoriteplaces.location.detail.data.remote

import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.Schedule
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
    fun toDomain() = Schedules(
        listOf(
            Schedule(
                Day.MONDAY,
                monday?.open ?: "",
                monday?.close ?: ""
            ),
            Schedule(
                Day.TUESDAY,
                tuesday?.open ?: "",
                tuesday?.close ?: ""
            ),
            Schedule(
                Day.WEDNESDAY,
                wednesday?.open ?: "",
                wednesday?.close ?: ""
            ),
            Schedule(
                Day.THURSDAY,
                thursday?.open ?: "",
                thursday?.close ?: ""
            ),
            Schedule(
                Day.FRIDAY,
                friday?.open ?: "",
                friday?.close ?: ""
            ),
            Schedule(
                Day.SATURDAY,
                saturday?.open ?: "",
                saturday?.close ?: ""
            ),
            Schedule(
                Day.SUNDAY,
                sunday?.open ?: "",
                sunday?.close ?: ""
            )
        )
    )
}