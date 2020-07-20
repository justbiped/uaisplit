package com.favoriteplaces.location.detail.data

import com.squareup.moshi.Json

data class LocationDetailRemoteEntity(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "review") val review: Double,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "about") val about: String,
    @field:Json(name = "phone") val phone: String,
    @field:Json(name = "address") val address: String,
    @field:Json(name = "schedules") val schedules: List<ScheduleRemoteEntity>
) {

    fun toDomain(): LocationDetail {
        return LocationDetail(
            id,
            name,
            review,
            type,
            about,
            phone,
            address,
            schedules.map { it.toDomain() })
    }
}

data class ScheduleRemoteEntity(
    @field:Json(name = "open") val open: String,
    @field:Json(name = "close") val close: String
) {
    fun toDomain() = Schedule(open, close)
}