package com.favoriteplaces.location.detail.data.remote

import com.favoriteplaces.location.detail.data.domain.LocationDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody

@Serializable
data class LocationDetailRemoteEntity(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("review") val review: Double,
    @SerialName("type") val type: String,
    @SerialName("about") val about: String,
    @SerialName("phone") val phone: String,
    @SerialName("adress") val address: String,
    @SerialName("schedule") val schedule: SchedulesRemoteEntity
) {

    companion object {
        fun fromResponseBody(responseBody: ResponseBody): LocationDetailRemoteEntity {
            val fixedPayload = responseBody.string().replace("[", "").replace("]", "")
            return Json.decodeFromString(fixedPayload)
        }
    }

    fun toDomain() =
        LocationDetail(
            id, name, review, type, about, phone, address, schedule.toDomain()
        )
}

