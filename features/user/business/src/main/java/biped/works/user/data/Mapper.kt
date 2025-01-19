package biped.works.user.data

import biped.works.database.user.UserEntity

internal fun UserEntity.toDomain() = User(
    id = id,
    name = name,
    email = email,
    picture = imageUrl
)

internal fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    email = email,
    imageUrl = picture
)