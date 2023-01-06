package biped.works.user.profile

import biped.works.user.profile.data.User
import biped.works.user.profile.data.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        userRepository.saveUser(user)
    }
}
