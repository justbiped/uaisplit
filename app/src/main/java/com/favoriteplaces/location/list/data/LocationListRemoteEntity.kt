package com.favoriteplaces.location.list.data

import com.favoriteplaces.location.Location
import com.squareup.moshi.Json

data class LocationListRemoteEntity(@field:Json(name = "listLocations") val locations: List<LocationRemoteEntity>) {

    fun toDomainLocations() = locations.map { locationRemote ->
        locationRemote.toDomain()
    }
}

data class LocationRemoteEntity(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "review") val review: Double,
    @field:Json(name = "type") val type: String
) {
    fun toDomain() = Location(id, name, review, type)
}