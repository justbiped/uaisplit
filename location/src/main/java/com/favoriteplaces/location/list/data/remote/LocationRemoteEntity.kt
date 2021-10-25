package com.favoriteplaces.location.list.data.remote

import com.favoriteplaces.location.list.data.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationRemoteEntity(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("review") val review: Double,
    @SerialName("type") val type: String
) {
    fun toDomain() =
        Location(id, name, review, type)
}