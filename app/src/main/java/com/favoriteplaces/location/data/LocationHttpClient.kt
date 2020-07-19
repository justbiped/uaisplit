package com.favoriteplaces.location.data

import retrofit2.http.GET

interface LocationHttpClient {

    @GET("/locations")
    suspend fun fetchLocations(): LocationListRemoteEntity
}