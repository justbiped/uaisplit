package com.favoriteplaces.location.data

import com.favoriteplaces.location.detail.data.LocationDetailRemoteEntity
import com.favoriteplaces.location.list.data.LocationListRemoteEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationHttpClient {

    @GET("/locations")
    suspend fun fetchLocations(): LocationListRemoteEntity

    @GET("/locations/{id}")
    suspend fun byId(@Path("id") locationId: Int): LocationDetailRemoteEntity
}