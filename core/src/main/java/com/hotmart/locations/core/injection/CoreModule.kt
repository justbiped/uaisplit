package com.hotmart.locations.core.injection

import com.hotmart.locations.core.http.HttpClient
import com.hotmart.locations.core.http.HttpManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    internal fun providesHttpManager(okHttpClient: OkHttpClient): HttpManager {
        val contentType = "application/json".toMediaType()
        val converterFactory = Json.asConverterFactory(contentType)
        return HttpManager(okHttpClient, converterFactory)
    }

    @Provides
    @Singleton
    internal fun providesHttpClient(): OkHttpClient = HttpClient().instantiate()
}