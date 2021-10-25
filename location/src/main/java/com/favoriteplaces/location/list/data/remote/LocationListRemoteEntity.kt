package com.favoriteplaces.location.list.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationListRemoteEntity(
    @SerialName("listLocations") val locations: List<LocationRemoteEntity>
) {
    fun toDomainLocations() = locations.map { locationRemote ->
        locationRemote.toDomain()
    }
}

