package com.favoriteplaces.location.detail.data

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody

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

    companion object {
        fun fromResponseBody(responseBody: ResponseBody): LocationDetailRemoteEntity {
            val fixedPayload = responseBody.string().replace("[", "").replace("]", "")

            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(LocationDetailRemoteEntity::class.java)

            return jsonAdapter.fromJson(fixedPayload)!!
        }
    }

    fun toDomain() = LocationDetail(
        id, name, review, type, about, phone, address, schedule.toDomain()
    )
}

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
            Schedule(Day.MONDAY, monday?.open ?: "", monday?.close ?: ""),
            Schedule(Day.TUESDAY, tuesday?.open ?: "", tuesday?.close ?: ""),
            Schedule(Day.WEDNESDAY, wednesday?.open ?: "", wednesday?.close ?: ""),
            Schedule(Day.THURSDAY, thursday?.open ?: "", thursday?.close ?: ""),
            Schedule(Day.FRIDAY, friday?.open ?: "", friday?.close ?: ""),
            Schedule(Day.SATURDAY, saturday?.open ?: "", saturday?.close ?: ""),
            Schedule(Day.SUNDAY, sunday?.open ?: "", sunday?.close ?: "")
        )
    )
}

data class ScheduleRemoteEntity(
    @field:Json(name = "open") val open: String,
    @field:Json(name = "close") val close: String
)