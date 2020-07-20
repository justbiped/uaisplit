package com.favoriteplaces.location.detail.data

import java.time.DayOfWeek

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
    val dayOfWeek: DayOfWeek,
    val open: String,
    val close: String
)