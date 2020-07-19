package com.favoriteplaces.location.data

import com.favoriteplaces.location.Location
import com.favoriteplaces.location.detail.LocationDetail
import javax.inject.Inject

class LocationRepository @Inject constructor(private val locationHttpClient: LocationHttpClient) {

    suspend fun fetchLocations(): List<Location> {
        val locationList = locationHttpClient.fetchLocations()
        return locationList.toDomainLocations()
    }

    suspend fun findLocationById(locationId: Int): LocationDetail {
        val locationDetail = locationHttpClient.byId(locationId)
        return locationDetail.toDomain()
    }
}