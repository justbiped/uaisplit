package com.favoriteplaces.core.extensions

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager

fun Context.registerLocalBroadcast(
    receiver: BroadcastReceiver,
    filter: IntentFilter
) {
    LocalBroadcastManager
        .getInstance(this)
        .registerReceiver(receiver, filter)
}

fun Context.unregisterLocalBroadcast(receiver: BroadcastReceiver) {
    LocalBroadcastManager
        .getInstance(this)
        .unregisterReceiver(receiver)
}