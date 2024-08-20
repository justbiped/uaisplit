package biped.works.statement.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface StatementApi {

    @GET("/transaction/statement/{entry}/{conclusion}")
    suspend fun getStatement(
        @Path("entry") entry: String,
        @Path("conclusion") conclusion: String
    ): RemoteStatement
}