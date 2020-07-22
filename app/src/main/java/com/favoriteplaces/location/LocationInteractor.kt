package com.favoriteplaces.location

import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.list.data.Location
import javax.inject.Inject

class LocationInteractor @Inject constructor(private val locationRepository: LocationRepository) {

    suspend fun loadLocations(): Result<List<Location>> {
        return try {
            Result.success(locationRepository.fetchLocations())
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }

    suspend fun loadLocationDetails(locationId: Int): Result<LocationDetail> {
        return try {
            Result.success(locationRepository.findLocationById(locationId))
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}