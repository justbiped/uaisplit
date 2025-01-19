package com.biped.works.profile

import com.biped.works.profile.data.Profile
import javax.inject.Inject
import kotlinx.coroutines.flow.map

class ObserveProfileUseCase @Inject constructor(
    private val userRepository: biped.works.user.data.UserRepository
) {
    operator fun invoke() = userRepository.userStream.map { user ->
        Profile(
            userId = user.id,
            name = user.name,
            email = user.email,
            picture = user.picture
        )
    }
}
