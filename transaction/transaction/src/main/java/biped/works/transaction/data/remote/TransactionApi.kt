package biped.works.transaction.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TransactionApi {

    @GET("/transaction/{id}")
    suspend fun getTransaction(@Path("id") id: String): RemoteTransaction

    @POST("/transaction")
    suspend fun saveTransaction(@Body transactionUpdate: RemoteTransactionUpdate): RemoteTransaction
}