package com.hotmart.locations.core.http

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Inject

class HttpManager @Inject constructor(
    private val httpClient: OkHttpClient,
    private val converterFactory: Converter.Factory
) {

    fun <T> instantiate(serverClass: Class<T>, baseUrl: String): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(serverClass)
    }
}