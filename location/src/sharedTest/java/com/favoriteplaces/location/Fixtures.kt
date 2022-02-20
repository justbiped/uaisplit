package com.favoriteplaces.location

import com.favoriteplaces.location.detail.data.remote.LocationDetailApiModel
import com.favoriteplaces.location.detail.data.remote.ScheduleRemoteEntity
import com.favoriteplaces.location.detail.data.remote.SchedulesApiModel

fun locationDetailsApiFixture(
    id: Int = 0,
    name: String = "Café da Japa",
    review: Double = 5.0,
    type: String = "Café",
    about: String = "O café mais chavoso que existe",
    phone: String = "+55 (37)982598716",
    address: String = "Rua Chavosa, Barrerão, Belo Horizonte",
    schedule: SchedulesApiModel = schedulesApiFixture()
) = LocationDetailApiModel(id, name, review, type, about, phone, address, schedule)

fun schedulesApiFixture() = SchedulesApiModel(wednesday = ScheduleRemoteEntity("11h", "14h"))