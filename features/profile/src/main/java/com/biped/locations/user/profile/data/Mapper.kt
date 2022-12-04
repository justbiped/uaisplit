package com.biped.locations.user.profile.data

import biped.works.database.user.UserEntity

internal fun UserEntity.toDomain(): User {
    return User(
        id = uid,
        name = name,
        picture = imageUrl
    )
}