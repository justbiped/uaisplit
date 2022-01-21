package com.favoriteplaces.location.di

import com.favoriteplaces.location.BuildConfig
import com.favoriteplaces.location.data.LocationHttpClient
import com.hotmart.locations.core.http.HttpManager
import com.hotmart.locations.core.injection.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class LocationModule {

    @Provides
    @FeatureScope
    fun providesLocationService(httpManager: HttpManager) =
        httpManager.instantiate(LocationHttpClient::class.java, BuildConfig.BASE_URL)
}