package biped.works.user.data

import android.util.Log
import biped.works.database.user.UserDao
import biped.works.database.user.UserEntity
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val coroutineScope: CoroutineScope
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
            "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
        )
        userDao.saveUser(userEntity)
    }

    suspend fun saveUser(user: User) = suspendCancellableCoroutine { continuation ->
        coroutineScope.launch {
            try {
                userDao.saveUser(user.toEntity())
                continuation.resume("")
            } catch (error: Throwable) {
                continuation.resumeWithException(error)
            }
        }
    }
}
