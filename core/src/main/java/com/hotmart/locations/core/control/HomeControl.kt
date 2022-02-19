package com.hotmart.locations.core.control

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.hotmart.locations.core.BuildConfig
import kotlinx.parcelize.Parcelize

@Parcelize
class HomeAction(val isNavBarVisible: Boolean = true) : Parcelable

fun Context.setHomeAction(action: HomeAction) {
    if (BuildConfig.BUILD_TYPE == "local") return

    val intent = Intent(HOME_ACTION_INTENT)
    intent.putExtra(HOME_ACTION, action)
    startActivity(intent)
}

const val HOME_ACTION_INTENT = "com.hotmart.lcoations.HOME_ACTION"
const val HOME_ACTION = "homeAction"