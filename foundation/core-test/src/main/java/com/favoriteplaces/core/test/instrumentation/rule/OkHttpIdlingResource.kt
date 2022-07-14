package com.favoriteplaces.core.test.instrumentation.rule

import androidx.test.espresso.IdlingResource
import okhttp3.OkHttpClient

class OkHttpIdlingResource(private val client: OkHttpClient) : IdlingResource {

    override fun getName(): String {
        return "OkHttp"
    }

    override fun isIdleNow(): Boolean {
        return client.dispatcher.runningCallsCount() == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        client.dispatcher.idleCallback = Runnable {
            callback?.run { onTransitionToIdle() }
        }
    }
}