package com.favoriteplaces.location.detail

import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.domain.ScheduleGroup

class LocationDetailUIModel(
    val name: String,
    val review: Double,
    val type: String,
    val about: String,
    val phone: String,
    val address: String,
    private val scheduleGroups: List<ScheduleGroup>
) {

    companion object {
        fun fromDomain(locationDetail: LocationDetail) = LocationDetailUIModel(
            locationDetail.name,
            locationDetail.review,
            locationDetail.type,
            locationDetail.about,
            locationDetail.phone,
            locationDetail.address,
            locationDetail.schedules.groupByWorkingTime()
        )
    }

    fun formattedSchedule(scheduleFormatter: ScheduleFormatter): String {
        val stringBuilder = StringBuilder()
        scheduleGroups.forEach {
            stringBuilder.append("${scheduleFormatter.format(it)}\n")
        }

        return stringBuilder.toString()
    }
}