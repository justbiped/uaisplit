package com.favoriteplaces.core.di

import android.content.Context
import biped.works.coroutines.DispatcherDefault
import biped.works.coroutines.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context

    @Provides
    @Singleton
    fun provideScopeIO() = CoroutineScope(
        SupervisorJob() + DispatcherProvider.IO + CoroutineName("Application Scope")
    )

    @Provides
    @Singleton
    @DispatcherDefault
    fun provideScopeDefault() = CoroutineScope(
        SupervisorJob() + DispatcherProvider.Default + CoroutineName("Application Scope")
    )
}