package com.favoriteplaces.location.detail.data

import com.squareup.moshi.Json
import java.time.DayOfWeek

data class LocationDetailRemoteEntity(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "review") val review: Double,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "about") val about: String,
    @field:Json(name = "phone") val phone: String,
    @field:Json(name = "adress") val address: String,
    @field:Json(name = "schedule") val schedule: SchedulesRemoteEntity
) {

    fun toDomain() = LocationDetail(
        id, name, review, type, about, phone, address, schedule.toScheduleList()
    )
}

data class SchedulesRemoteEntity(
    @field:Json(name = "monday") val monday: ScheduleRemoteEntity,
    @field:Json(name = "tuesday") val tuesday: ScheduleRemoteEntity,
    @field:Json(name = "wednesday") val wednesday: ScheduleRemoteEntity,
    @field:Json(name = "thursday") val thursday: ScheduleRemoteEntity,
    @field:Json(name = "friday") val friday: ScheduleRemoteEntity,
    @field:Json(name = "saturday") val saturday: ScheduleRemoteEntity,
    @field:Json(name = "sunday") val sunday: ScheduleRemoteEntity
) {
    fun toScheduleList() = listOf(
        Schedule(DayOfWeek.valueOf("MONDAY"), monday.open, monday.close),
        Schedule(DayOfWeek.valueOf("TUESDAY"), monday.open, monday.close),
        Schedule(DayOfWeek.valueOf("WEDNESDAY"), monday.open, monday.close),
        Schedule(DayOfWeek.valueOf("THURSDAY"), monday.open, monday.close),
        Schedule(DayOfWeek.valueOf("FRIDAY"), monday.open, monday.close),
        Schedule(DayOfWeek.valueOf("SATURDAY"), monday.open, monday.close),
        Schedule(DayOfWeek.valueOf("SUNDAY"), monday.open, monday.close)
    )
}

data class ScheduleRemoteEntity(
    @field:Json(name = "open") val open: String,
    @field:Json(name = "close") val close: String
)