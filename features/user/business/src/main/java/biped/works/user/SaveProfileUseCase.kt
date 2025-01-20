package biped.works.user

import biped.works.user.data.User
import biped.works.user.data.UserRepository
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): Result<String> {
        return try {
            userRepository.saveUser(user)
            Result.success("")
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}
