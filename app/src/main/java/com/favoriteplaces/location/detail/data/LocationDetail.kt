package com.favoriteplaces.location.detail.data

data class LocationDetail(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
    val about: String,
    val phone: String,
    val address: String,
    val schedules: List<Schedule>
)

data class Schedule(
    val open: String,
    val close: String
)