package biped.works.user.data

import biped.works.database.user.UserEntity
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class UserRepository @Inject constructor(
    private val userDao: biped.works.database.user.UserDao,
    private val coroutineScope: CoroutineScope
) {

    val userStream: Flow<User> = userDao.getUserStream()
        .onStart { saveTestUser() }
        .map { it.toDomain() }

    private suspend fun saveTestUser() {
        val userEntity = UserEntity(
            UUID.randomUUID().toString(),
            "Roubert Edgar",
            "roubertedgar@gmail.com",
            "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
        )
        userDao.saveUser(userEntity)
    }

    suspend fun saveUser(user: User) = coroutineScope.async {
        userDao.saveUser(user.toEntity())
    }.await()
}

