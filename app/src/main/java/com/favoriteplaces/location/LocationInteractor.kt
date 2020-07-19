package com.favoriteplaces.location

import com.favoriteplaces.location.data.LocationRepository
import javax.inject.Inject

class LocationInteractor @Inject constructor(private val locationRepository: LocationRepository) {

    suspend fun loadLocations(): Result<List<Location>> {
        return try {
            Result.success(locationRepository.fetchLocations())
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}