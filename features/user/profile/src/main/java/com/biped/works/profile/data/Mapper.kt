package com.biped.works.profile.data

import com.biped.works.profile.ui.ProfileUiModel

internal fun Profile.toUiModel() = ProfileUiModel(userId, name, email, picture)

internal fun ProfileUiModel.toDomain() = Profile(userId, name, email, picture)