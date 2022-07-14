package com.favoriteplaces.location.list

import com.favoriteplaces.location.data.LocationRepository
import com.favoriteplaces.location.list.data.Location
import javax.inject.Inject

internal class LoadLocations @Inject constructor(private val locationRepository: LocationRepository) {

    suspend operator fun invoke(): Result<List<Location>> {
        return try {
            Result.success(locationRepository.fetchLocations())
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}