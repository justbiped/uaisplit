package com.favoriteplaces.location.data

import com.favoriteplaces.location.detail.data.LocationDetail
import com.favoriteplaces.location.detail.data.LocationDetailRemoteEntity
import com.favoriteplaces.location.list.data.Location
import okhttp3.ResponseBody
import javax.inject.Inject


class LocationRepository @Inject constructor(private val locationHttpClient: LocationHttpClient) {

    suspend fun fetchLocations(): List<Location> {
        val locationList = locationHttpClient.fetchLocations()
        return locationList.toDomainLocations()
    }

    suspend fun findLocationById(locationId: Int): LocationDetail {
        val locationDetailResponse = locationHttpClient.byId(locationId)
        return parseLocationDetailResponse(locationDetailResponse)
    }

    private fun parseLocationDetailResponse(responseBody: ResponseBody): LocationDetail {
        return LocationDetailRemoteEntity.fromResponseBody(responseBody).toDomain()
    }
}