package biped.works.api.http

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

object ApiBuilder {

    private val builder = Retrofit.Builder()

    fun client(okHttpClient: OkHttpClient): ApiBuilder {
        builder.client(okHttpClient)
        return this
    }

    fun converter(factory: Converter.Factory): ApiBuilder {
        builder.addConverterFactory(factory)
        return this
    }

    fun baseUrl(url: String): ApiBuilder {
        builder.baseUrl(url)
        return this
    }

    fun <T> create(serverClass: Class<T>): T {
        return builder
            .build()
            .create(serverClass)
    }
}