package com.favoriteplaces.location.injection

import com.favoriteplaces.BuildConfig
import com.favoriteplaces.core.http.HttpManager
import com.favoriteplaces.core.injection.FeatureScope
import com.favoriteplaces.location.data.LocationHttpClient
import dagger.Module
import dagger.Provides

@Module
class LocationModule {

    @Provides
    @FeatureScope
    fun providesLocationService(httpManager: HttpManager) =
        httpManager.instantiate(LocationHttpClient::class.java, BuildConfig.BASE_URL)

}