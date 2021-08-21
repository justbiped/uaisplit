package com.hotmart.locations.core.injection

import android.content.Context
import com.hotmart.locations.core.http.HttpManager
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun getContext(): Context

    fun getHttpManager(): HttpManager

    fun getOkHttpClient(): OkHttpClient
}