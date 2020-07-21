package com.favoriteplaces.location.detail

import com.favoriteplaces.location.detail.data.LocationDetail
import com.favoriteplaces.location.detail.data.ScheduleGroup

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

    fun formattedSchedule(): String {
        val stringBuilder = StringBuilder()

        scheduleGroups.forEach { group ->
            val formattedShortWeekDays = formatShortWeekDays(group)
            stringBuilder.append("$formattedShortWeekDays: ${group.open} às ${group.close}\n")
        }

        return stringBuilder.toString()

    }

    private fun formatShortWeekDays(group: ScheduleGroup): String {
        val shortWeekDays = group.shortWeekDays()

        fun isOneDay() = shortWeekDays.size == 1
        fun isFewDaysInSequence() = group.isInSequence() && shortWeekDays.size > 3

        return when {
            isOneDay() -> shortWeekDays.first()
            isFewDaysInSequence() -> "${shortWeekDays.first()} a ${shortWeekDays.last()}"
            else -> joinWithComma(shortWeekDays)
        }
    }

    private fun joinWithComma(shortWeekDays: List<String>): String {
        return shortWeekDays.joinToString().let {
            val separator = ", "
            val lastCommaIndex = it.lastIndexOf(separator)
            it.replaceRange(lastCommaIndex, lastCommaIndex + separator.length, " e ")
        }
    }
}