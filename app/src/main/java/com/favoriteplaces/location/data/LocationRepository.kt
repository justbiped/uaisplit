package com.favoriteplaces.location.data

import com.favoriteplaces.location.Location
import javax.inject.Inject

class LocationRepository @Inject constructor(private val locationHttpClient: LocationHttpClient) {

    suspend fun fetchLocations(): List<Location> {
        val locationList = locationHttpClient.fetchLocations()
        return locationList.toDomainLocations()
    }
}