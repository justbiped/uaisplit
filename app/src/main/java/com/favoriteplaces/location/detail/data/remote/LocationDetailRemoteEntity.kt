package com.favoriteplaces.location.detail.data.remote

import com.favoriteplaces.location.detail.data.domain.LocationDetail
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

    fun toDomain() =
        LocationDetail(
            id, name, review, type, about, phone, address, schedule.toDomain()
        )
}

