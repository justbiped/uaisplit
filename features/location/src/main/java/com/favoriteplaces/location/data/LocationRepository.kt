package com.favoriteplaces.location.data

import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.remote.LocationDetailApiModel
import com.favoriteplaces.location.list.data.Location
import javax.inject.Inject
import okhttp3.ResponseBody

internal class LocationRepository @Inject constructor(private val locationHttpClient: LocationHttpClient) {

    suspend fun fetchLocations(): List<Location> {
        val locationList = locationHttpClient.fetchLocations()
        return locationList.toDomainLocations()
    }

    suspend fun findLocationById(locationId: Int): LocationDetail {
        val locationDetailResponse = locationHttpClient.byId(locationId)
        return parseLocationDetailResponse(locationDetailResponse)
    }

    private fun parseLocationDetailResponse(responseBody: ResponseBody): LocationDetail {
        return LocationDetailApiModel.fromResponseBody(responseBody).toDomain()
    }
}