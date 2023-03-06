package com.biped.works.profile

import com.biped.works.profile.data.Profile
import biped.works.user.data.User
import biped.works.user.data.UserRepository
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(profile: Profile) {
        userRepository.saveUser(
            User(profile.userId, profile.name, profile.email, profile.picture)
        )
    }
}
