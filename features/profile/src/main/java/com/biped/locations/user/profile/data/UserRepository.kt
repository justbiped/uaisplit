package com.biped.locations.user.profile.data

import javax.inject.Inject
import kotlinx.coroutines.flow.flowOf

class UserRepository @Inject constructor() {

    val userStream = flowOf(
        User(
            id = "id",
            name = "R.Edgar",
            picture = "https://media-exp1.licdn.com/dms/image/C4D03AQFkXBIUIWdT2g/profile-displayphoto-shrink_400_400/0/1517979972037?e=1675900800&v=beta&t=PcXI0CPRdiMD8FTi2YAEyKtZUueQRZhgzpvTq5wM3U4",
        )
    )
}
