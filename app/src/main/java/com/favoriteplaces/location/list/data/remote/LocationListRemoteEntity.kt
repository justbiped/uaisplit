package com.favoriteplaces.location.list.data.remote

import com.squareup.moshi.Json

data class LocationListRemoteEntity(@field:Json(name = "listLocations") val locations: List<LocationRemoteEntity>) {

    fun toDomainLocations() = locations.map { locationRemote ->
        locationRemote.toDomain()
    }
}

