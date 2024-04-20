package biped.works.api.injection

import biped.works.api.asConverterFactory
import biped.works.api.http.ApiBuilder
import biped.works.api.http.HttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class APIModule {
    @Provides
    @Singleton
    internal fun providesHttpClient(): OkHttpClient = HttpClient().instantiate()

    @Provides
    @Singleton
    internal fun providesApiFactory(okHttpClient: OkHttpClient): ApiBuilder {
        val converterFactory = Json.asConverterFactory("application/json")

        return ApiBuilder
            .client(okHttpClient)
            .converter(converterFactory)
            .baseUrl("http::/localhost:8080")

    }
}