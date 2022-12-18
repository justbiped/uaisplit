package com.biped.locations.user.profile

import com.biped.locations.user.profile.data.User
import com.biped.locations.user.profile.data.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        userRepository.saveUser(user)
    }
}
