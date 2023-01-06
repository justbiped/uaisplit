package biped.works.user.profile.data

import biped.works.database.user.UserDao
import biped.works.database.user.UserEntity
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    val userStream: Flow<User> = userDao.getUserStream()
        .onStart { saveTestUser() }
        .distinctUntilChanged()
        .map { it.toDomain() }

    private suspend fun saveTestUser() {
        val userEntity = UserEntity(
            UUID.randomUUID().toString(),
            "Roubert Edgar",
            "roubertedgar@gmail.com",
            "https://media-exp1.licdn.com/dms/image/C4D03AQFkXBIUIWdT2g/profile-displayphoto-shrink_400_400/0/1517979972037?e=1675900800&v=beta&t=PcXI0CPRdiMD8FTi2YAEyKtZUueQRZhgzpvTq5wM3U4"
        )
        userDao.saveUser(userEntity)
    }

    suspend fun saveUser(user: User) {
        userDao.saveUser(user.toEntity())
    }
}
