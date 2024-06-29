package biped.works.statement.data.remote

import retrofit2.http.GET

interface StatementApi {

    @GET("/transaction/statement")
    suspend fun getStatement(): RemoteStatement
}