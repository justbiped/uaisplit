package com.favoriteplaces.core.test.instrumentation.rule

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.QueueDispatcher
import org.junit.rules.ExternalResource

class HttpResources : ExternalResource() {
    private val mockWebServer = MockWebServer()

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
}

class ClearQueueDispatcher : QueueDispatcher() {
    fun clear() {
        responseQueue.clear()
    }
}