package com.favoriteplaces.location.list.data.ui

import com.favoriteplaces.R
import com.favoriteplaces.location.list.data.Location
import kotlin.random.Random

data class LocationUIModel(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
    val image: LocationImageUIModel
) {

    companion object {
        fun fromDomain(location: Location) =
            LocationUIModel(
                location.id,
                location.name,
                location.review,
                location.type,
                LocationImageUIModel(
                    getPlaceHolderColor(),
                    ""
                )
            )

        private fun getPlaceHolderColor() = when (Random.nextInt(0, 4)) {
            0 -> R.color.turquoise_100
            1 -> R.color.red_100
            else -> R.color.yellow_200
        }
    }
}

