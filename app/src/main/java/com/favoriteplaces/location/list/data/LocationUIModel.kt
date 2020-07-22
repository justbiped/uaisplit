package com.favoriteplaces.location.list.data

data class LocationUIModel(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
    val image: String
) {

    companion object {
        fun fromDomain(location: Location) =
            LocationUIModel(
                location.id,
                location.name,
                location.review,
                location.type,
                ""
            )
    }
}