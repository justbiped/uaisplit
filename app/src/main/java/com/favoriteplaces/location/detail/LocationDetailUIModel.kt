package com.favoriteplaces.location.detail

import com.favoriteplaces.location.detail.data.LocationDetail

class LocationDetailUIModel(
    val name: String,
    val review: Double,
    val type: String,
    val about: String,
    val phone: String,
    val address: String,
    val schedules: String
) {

    companion object {
        fun fromDomain(locationDetail: LocationDetail) = LocationDetailUIModel(
            locationDetail.name,
            locationDetail.review,
            locationDetail.type,
            locationDetail.about,
            locationDetail.phone,
            locationDetail.address,
            ""
        )
    }
}