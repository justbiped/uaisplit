package biped.works.automation

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import okhttp3.Response as HttpResponse

interface GitHubApi {
    @GET("repos/justbiped/uaisplit/git/refs")
    suspend fun listRefs(): List<Reference>

    @POST("repos/justbiped/uaisplit/git/tags")
    suspend fun createTagObject(@Body tag: Tag): TagObject

    @POST("/repos/justbiped/uaisplit/git/refs")
    suspend fun createTagReference(@Body request: RefRequest): Reference

    @POST("repos/justbiped/uaisplit/releases")
    suspend fun createRelease(@Body release: Release): ReleaseUrl
}

@Serializable
data class Release(
    @SerialName("name") val name: String,
    @SerialName("body") val description: String,
    @SerialName("tag_name") val tag: String,
    @SerialName("target_commitish") val sourceBranch: String,
    @SerialName("draft") val draft: Boolean,
    @SerialName("prerelease") val preRelease: Boolean,
    @SerialName("generate_release_notes") val generateReleaseNotes: Boolean,
)

@Serializable
data class ReleaseUrl(@SerialName("html_url") val url: String)

@Serializable
data class TagObject(
    @SerialName("node_id") val nodeId: String,
    @SerialName("tag") val tag: String,
    @SerialName("sha") val sha: String,
    @SerialName("url") val url: String,
    @SerialName("message") val message: String,
    @SerialName("object") val objectt: Object,
)

@Serializable
data class Tag(
    @SerialName("tag") val tag: String,
    @SerialName("message") val message: String,
    @SerialName("object") val sha: String,
    @SerialName("type") val type: String,
)

@Serializable
data class Reference(
    @SerialName("ref") val ref: String,
    @SerialName("node_id") val nodeId: String,
    @SerialName("url") val url: String,
    @SerialName("object") val objectt: Object,
)

@Serializable
data class RefRequest(
    @SerialName("ref") val ref: String,
    @SerialName("sha") val sha: String,
)

@Serializable
data class Object(
    @SerialName("sha") val sha: String,
    @SerialName("type") val type: String,
    @SerialName("url") val url: String,
)

private val json = Json { ignoreUnknownKeys = true }
fun createGitHubApi(): GitHubApi {
    val contentType = "application/json".toMediaType()
    val converterFactory = json.asConverterFactory(contentType)

    val client = OkHttpClient
        .Builder()
        .addInterceptor(HeaderInterceptor())
        .build()
    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(client)
        .addConverterFactory(converterFactory)
        .build()
        .create(GitHubApi::class.java)
}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): HttpResponse {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/vnd.github+json")
            .addHeader("Authorization", "Bearer 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")
            .addHeader("X-GitHub-Api-Version", "2022-11-28")
            .build()
        return chain.proceed(request)
    }
}