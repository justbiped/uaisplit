package com.favoriteplaces.location.detail.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleRemoteEntity(
    @SerialName("open") val open: String,
    @SerialName("close") val close: String
)