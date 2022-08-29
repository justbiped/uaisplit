package com.favoriteplaces.location

import com.favoriteplaces.location.detail.data.remote.LocationDetailApiModel
import com.favoriteplaces.location.detail.data.remote.ScheduleRemoteEntity
import com.favoriteplaces.location.detail.data.remote.SchedulesApiModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal fun locationDetailsApiFixture(
    id: Int = 0,
    name: String = "My Coffee",
    review: Double = 5.0,
    type: String = "Café",
    about: String = "O café mais chavoso que existe",
    phone: String = "+55 (37)982598716",
    address: String = "Rua Chavosa, Barrerão, Belo Horizonte",
    schedule: SchedulesApiModel = schedulesApiFixture()
) = LocationDetailApiModel(id, name, review, type, about, phone, address, schedule)

internal fun schedulesApiFixture() =
    SchedulesApiModel(wednesday = ScheduleRemoteEntity("11h", "14h"))

fun locationDetailsApiFixtureJson(
    id: Int = 0,
    name: String = "Café da Japa",
    review: Double = 5.0,
    type: String = "Café",
    about: String = "O café mais chavoso que existe",
    phone: String = "+55 (37)982598716",
    address: String = "Rua Chavosa, Barrerão, Belo Horizonte",
    schedule: String = schedulesApiFixtureJson()
): String = Json.encodeToString(
    LocationDetailApiModel(
        id,
        name,
        review,
        type,
        about,
        phone,
        address,
        Json.decodeFromString(schedule)
    )
)

internal fun schedulesApiFixtureJson() =
    Json.encodeToString(SchedulesApiModel(wednesday = ScheduleRemoteEntity("11h", "14h")))
