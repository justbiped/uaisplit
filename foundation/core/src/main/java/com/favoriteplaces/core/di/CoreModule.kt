package com.favoriteplaces.core.di

import android.content.Context
import biped.works.coroutines.DispatcherDefault
import biped.works.coroutines.DispatcherProvider
import biped.works.coroutines.DispatcherIO
import com.favoriteplaces.core.http.HttpClient
import com.favoriteplaces.core.http.HttpManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient

private val json = Json {
    ignoreUnknownKeys = true
}

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    internal fun providesHttpClient(): OkHttpClient = HttpClient().instantiate()

    @Provides
    @Singleton
    internal fun providesHttpManager(okHttpClient: OkHttpClient): HttpManager {
        val contentType = "application/json".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)
        return HttpManager(okHttpClient, converterFactory)
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context) = context

    @Provides
    @Singleton
    fun provideScopeIO() = CoroutineScope(SupervisorJob() + DispatcherProvider.IO + CoroutineName("Application Scope"))

    @Provides
    @Singleton
    @DispatcherDefault
    fun provideScopeDefault() =
        CoroutineScope(SupervisorJob() + DispatcherProvider.Default + CoroutineName("Application Scope"))
}