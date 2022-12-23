package com.biped.locations.user.profile

import com.biped.locations.user.profile.data.UserRepository
import javax.inject.Inject

class ObserveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.userStream
}
