package com.favoriteplaces.tools

import androidx.test.espresso.IdlingRegistry
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.ExternalResource

class HttpResources : ExternalResource() {
    private val mockWebServer = MockWebServer()

    private var httpIdlingResource: OkHttpIdlingResource? = null

    override fun before() {
        mockWebServer.start(8080)
    }

    override fun after() {
        mockWebServer.shutdown()
    }

    fun init() {
        before()
    }

    fun release() {
        after()
    }

    fun mockHttpResponse(response: MockResponse) {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return response
            }
        }
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