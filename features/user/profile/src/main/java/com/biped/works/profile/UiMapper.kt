package com.biped.works.profile

import biped.works.user.data.User

internal fun User.toProfile() = ProfileUiModel(id, name, email, picture)

internal fun ProfileUiModel.toUser() = User(userId, name, email, picture)