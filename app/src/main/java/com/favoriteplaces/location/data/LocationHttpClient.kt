package com.favoriteplaces.location.data

import com.favoriteplaces.location.list.data.LocationListRemoteEntity
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationHttpClient {

    @GET("/locations")
    suspend fun fetchLocations(): LocationListRemoteEntity

    @GET("/locations/{id}")
    suspend fun byId(@Path("id") locationId: Int): ResponseBody
}