package com.biped.locations.user.profile.data

import com.biped.locations.user.profile.data.User
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOf

class UserRepository @Inject constructor() {

    fun observeUser() = flowOf(
        User(
            id = "id",
            name = "R.Edgar",
            picture = "https://media-exp1.licdn.com/dms/image/C4D03AQFkXBIUIWdT2g/profile-displayphoto-shrink_800_800/0/1517979972037?e=1668038400&v=beta&t=xJeY7HQEtSnDNhByyvi7Mas26NtR5c0OlR-TqJMpc88",
        )
    )
}
