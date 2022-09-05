package com.favoriteplaces.location.di

import com.favoriteplaces.location.BuildConfig
import com.favoriteplaces.location.data.LocationHttpClient
import com.favoriteplaces.core.http.HttpManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal class LocationModule {

    @Provides
    @ViewModelScoped
    fun providesLocationService(httpManager: HttpManager): LocationHttpClient =
        httpManager.instantiate(LocationHttpClient::class.java, BuildConfig.BASE_URL)
}