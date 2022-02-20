package com.hotmart.tests.tools

import androidx.test.espresso.IdlingRegistry
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.QueueDispatcher
import org.junit.rules.ExternalResource

class HttpResources : ExternalResource() {
    private val mockWebServer = MockWebServer()

    private var httpIdlingResource: OkHttpIdlingResource? = null

    override fun before() {
        mockWebServer.dispatcher = ClearQueueDispatcher()
        mockWebServer.start(8080)
    }

    override fun after() {
        try {
            mockWebServer.close()
        } catch (error: Throwable) {
            print("Forced server shutdown")
        }
    }

    fun init() {
        before()
    }

    fun release() {
        after()
    }

    fun enqueue(vararg response: MockResponse) {
        response.forEach { mockWebServer.enqueue(it) }
    }

    fun clear() {
        (mockWebServer.dispatcher as ClearQueueDispatcher).clear()
    }

    fun registerIdling(client: OkHttpClient) {
        httpIdlingResource = OkHttpIdlingResource(client)
        IdlingRegistry.getInstance().register(httpIdlingResource!!)
    }

    fun unregisterIdling() {
        IdlingRegistry.getInstance().unregister(httpIdlingResource!!)
        httpIdlingResource = null
    }
}

class ClearQueueDispatcher : QueueDispatcher() {
    fun clear() {
        responseQueue.clear()
    }
}