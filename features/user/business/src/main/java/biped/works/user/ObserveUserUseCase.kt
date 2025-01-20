package biped.works.user

import biped.works.user.data.UserRepository
import javax.inject.Inject

class ObserveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.userStream
}
