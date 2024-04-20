package biped.works.api.http

import biped.works.api.BuildConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class HttpClient @Inject constructor() {

    fun instantiate(interceptors: List<Interceptor> = emptyList()): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptors)
            .build()
    }
}

fun OkHttpClient.Builder.addInterceptor(interceptors: List<Interceptor> = emptyList()): OkHttpClient.Builder {
    interceptors.forEach {
        addInterceptor(it)
    }
    return this
}