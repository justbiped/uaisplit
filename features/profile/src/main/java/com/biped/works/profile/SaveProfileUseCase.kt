package com.biped.works.profile

import biped.works.user.data.User
import biped.works.user.data.UserRepository
import com.biped.works.profile.data.Profile
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(profile: Profile): Result<String> {
        return try {
            userRepository.saveUser(
                User(profile.userId, profile.name, profile.email, profile.picture)
            )
            Result.success("")
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}
