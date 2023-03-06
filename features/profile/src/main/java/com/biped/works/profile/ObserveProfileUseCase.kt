package com.biped.works.profile

import biped.works.user.data.UserRepository
import com.biped.works.profile.data.Profile
import javax.inject.Inject
import kotlinx.coroutines.flow.map

class ObserveProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.userStream.map {
        Profile(
            userId = it.id,
            name = it.name,
            email = it.email,
            picture = it.picture
        )
    }
}
