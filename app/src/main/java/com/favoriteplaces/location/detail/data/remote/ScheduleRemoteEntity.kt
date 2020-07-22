package com.favoriteplaces.location.detail.data.remote

import com.squareup.moshi.Json

data class ScheduleRemoteEntity(
    @field:Json(name = "open") val open: String,
    @field:Json(name = "close") val close: String
)