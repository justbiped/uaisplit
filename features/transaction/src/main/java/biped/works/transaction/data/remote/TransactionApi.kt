package biped.works.transaction.data.remote

import biped.works.transaction.data.Transaction
import retrofit2.http.GET
import retrofit2.http.Path

interface TransactionApi {
    @GET("operation/{entry}/{conclusion}")
    fun fetchTransactions(
        @Path("entry") entry: String = "2023-03-30",
        @Path("conclusion") conclusion: String = "2023-03-30"
    ): List<Transaction>

}