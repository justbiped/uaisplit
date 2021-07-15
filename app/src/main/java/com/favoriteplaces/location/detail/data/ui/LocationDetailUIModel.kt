package com.favoriteplaces.location.detail.data.ui

import com.favoriteplaces.location.detail.ui.ScheduleFormatter
import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.domain.ScheduleGroup

class LocationDetailUIModel(
    val name: String,
    val rating: Double,
    val type: String,
    val about: String,
    val phone: String,
    val address: String,
    val locationReviews: List<LocationReviewUIModel>,
    private val scheduleGroups: List<ScheduleGroup>
) {

    companion object {
        fun fromDomain(locationDetail: LocationDetail) =
            LocationDetailUIModel(
                locationDetail.name,
                locationDetail.review,
                locationDetail.type,
                locationDetail.about,
                locationDetail.phone,
                locationDetail.address,
                buildMockedReviews(),
                locationDetail.schedules.groupByWorkingTime()
            )

        private fun buildMockedReviews(): List<LocationReviewUIModel> {
            return listOf(
                LocationReviewUIModel(
                    "",
                    "Fantástico!!",
                    "Tortas deliciosas. Os waffles também estavam muito bons. Equipe muito atenciosa. :)",
                    "Tomás Montenegro, Belo Horizonte - MG",
                    5.0
                ),
                LocationReviewUIModel(
                    "",
                    "Café da manhã delicioso",
                    "Nós fomos para o brunch e estava realmente delicioso. Pães, ovos, café, sucos naturais. Não é muito barato mas vale a pena.",
                    "Glória Ruiz, São João Del Rey - MG",
                    4.0
                ),
                LocationReviewUIModel(
                    "",
                    "Ótima comida",
                    "Comidas frescas e de boa qualidade. Pães e quitandas saindo do forno toda hora. Cafés especiais e ambiente agradável.",
                    "Shirley Jones, Mountain View - CA",
                    4.0
                )
            )
        }
    }

    fun formattedSchedule(scheduleFormatter: ScheduleFormatter): String {
        val stringBuilder = StringBuilder()
        scheduleGroups.forEach {
            stringBuilder.append("${scheduleFormatter.format(it)}\n")
        }

        return stringBuilder.toString()
    }
}

