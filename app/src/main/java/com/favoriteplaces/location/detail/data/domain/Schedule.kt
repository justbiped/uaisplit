package com.favoriteplaces.location.detail.data.domain

import com.favoriteplaces.location.detail.data.domain.Day

data class Schedule(
    val day: Day,
    val open: String,
    val close: String
)