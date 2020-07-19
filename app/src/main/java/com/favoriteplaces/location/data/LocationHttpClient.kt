package com.favoriteplaces.location.data

import com.favoriteplaces.location.list.data.LocationListRemoteEntity
import retrofit2.http.GET

interface LocationHttpClient {

    @GET("/locations")
    suspend fun fetchLocations(): LocationListRemoteEntity
}