package com.favoriteplaces.location.detail.data.ui

import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.ui.ScheduleFormatter

internal class LocationDetailUIModel(
    val name: String,
    val rating: Double,
    val type: String,
    val about: String,
    val phone: String,
    val address: String,
    val locationReviews: List<LocationReviewUIModel>,
    val schedule: String
) {

    companion object {
        fun fromDomain(locationDetail: LocationDetail, scheduleFormatter: ScheduleFormatter) =
            LocationDetailUIModel(
                locationDetail.name,
                locationDetail.review,
                locationDetail.type,
                locationDetail.about,
                locationDetail.phone,
                locationDetail.address,
                buildMockedReviews(),
                scheduleFormatter.format(locationDetail.schedule)
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
}

