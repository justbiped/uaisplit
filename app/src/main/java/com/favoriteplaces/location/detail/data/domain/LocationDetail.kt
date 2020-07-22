package com.favoriteplaces.location.detail.data.domain

import com.favoriteplaces.location.detail.data.domain.Schedules

data class LocationDetail(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
    val about: String,
    val phone: String,
    val address: String,
    val schedules: Schedules
)