package com.favoriteplaces.location.list.data.remote

import com.favoriteplaces.location.list.data.Location
import com.squareup.moshi.Json

data class LocationRemoteEntity(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "review") val review: Double,
    @field:Json(name = "type") val type: String
) {
    fun toDomain() =
        Location(id, name, review, type)
}