package com.favoriteplaces.location

import com.favoriteplaces.location.list.data.Location
import com.favoriteplaces.location.list.data.ui.LocationUIModel

internal fun locationFixture(
    id: Int = 0,
    name: String = "Some Location",
    review: Double = 4.5,
    type: String = "Pub"
) = Location(
    id,
    name,
    review,
    type
)

internal fun locationUiFixture(
    id: Int = 0,
    name: String = "Some Location",
    review: Double = 4.5,
    type: String = "Pub",
) = LocationUIModel(
    id,
    name,
    review,
    type,
    "",
)