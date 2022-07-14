package com.biped.locations.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.favoriteplaces.core.control.HOME_ACTION
import com.favoriteplaces.core.control.HOME_ACTION_INTENT
import com.favoriteplaces.core.control.HomeAction

class HomeActionReceiver : BroadcastReceiver() {
    private var onActionReceived: (HomeAction) -> Unit = {}

    fun onReceiveAction(onActionReceived: (HomeAction) -> Unit = {}) {
        this.onActionReceived = onActionReceived
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == HOME_ACTION_INTENT && intent.hasExtra(HOME_ACTION)) {
            val action = intent.getParcelableExtra(HOME_ACTION) ?: HomeAction()
            onActionReceived(action)
        }
    }
}