package biped.works.user.profile

import biped.works.user.profile.data.UserRepository
import javax.inject.Inject

class ObserveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.userStream
}
