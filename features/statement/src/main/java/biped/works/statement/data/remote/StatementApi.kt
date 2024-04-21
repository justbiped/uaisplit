package biped.works.statement.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface StatementApi {

    @GET("/transaction/statement")
    suspend fun getStatement(): RemoteStatement
}