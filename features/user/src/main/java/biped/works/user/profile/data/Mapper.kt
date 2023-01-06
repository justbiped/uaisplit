package biped.works.user.profile.data

import biped.works.database.user.UserEntity

internal fun UserEntity.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email,
        picture = imageUrl
    )
}

internal fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    email = email,
    imageUrl = picture
)