package com.favoriteplaces.location.detail.data

import com.favoriteplaces.location.detail.LocationDetail

data class LocationDetailRemoteEntity(val id: Int) {

    fun toDomain(): LocationDetail {
        return LocationDetail(2)
    }
}