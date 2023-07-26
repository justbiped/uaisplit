package biped.works.transaction.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface TransactionApi {

    @GET("operation/{entry}/{conclusion}")
    suspend fun getTransactions(
        @Path("entry") entry: String,
        @Path("conclusion") conclusion: String
    ): List<RemoteTransaction>
}

fun getTransactionApiInstance(): TransactionApi {
    val contentType = "application/json".toMediaType()
    val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(contentType))
        .baseUrl("https://uaisplit.up.railway.app/")
        .build()

    return retrofit.create(TransactionApi::class.java)
}