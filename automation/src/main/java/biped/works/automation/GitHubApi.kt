package biped.works.automation

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.ByteString.Companion.decodeBase64
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import okhttp3.Response as HttpResponse

interface GitHubApi {
    @GET("/repos/justbiped/uaisplit/git/refs")
    fun listRefs(): Call<List<ReferenceResponse>>

    @POST("/repos/justbiped/uaisplit/git/refs")
    fun createReference(@Body request: ReferenceRequest): Call<ReferenceResponse>

    @GET("/repos/justbiped/uaisplit/contents/version.properties")
    fun readVersionsVile(@Query("ref") ref: String = "heads/version-bump"): Call<FileRequest>

    @PUT("/repos/justbiped/uaisplit/contents/version.properties")
    fun updateVersionFile(@Body file: FileUpdateRequest): Call<FileUpdateResponse>

    @POST("/repos/justbiped/uaisplit/pulls")
    fun createPullRequest(@Body pullRequest: PullRequest): Call<PullResponse>

    @GET("/repos/justbiped/uaisplit/releases")
    fun getLastReleases(@Query("page") page: Int = 1, @Query("perPage") perPage: Int = 3): Call<List<ReleaseResponse>>

    @POST("/repos/justbiped/uaisplit/releases")
    fun createRelease(@Body release: ReleaseRequest): Call<ReleaseResponse>

    @PATCH("/repos/justbiped/uaisplit/releases/{id}")
    fun updateRelease(@Path("id") id: String, @Body release: ReleaseRequest): Call<ReleaseResponse>
}

@Serializable
data class PullRequest(
    @SerialName("title") val title: String,
    @SerialName("body") val body: String,
    @SerialName("head") val head: String,
    @SerialName("base") val base: String,
)

@Serializable
data class PullResponse(
    @SerialName("html_url") val url: String,
)

@Serializable
data class FileRequest(
    @SerialName("name") val name: String,
    @SerialName("path") val path: String,
    @SerialName("type") val type: String,
    @SerialName("encoding") val encoding: String,
    @SerialName("content") val content: String,
    @SerialName("sha") val sha: String
) {
    val decodedContent: String = content
        .decodeBase64()
        .toString()
        .replace(Regex("text=|\\[|\\]"), "")
}

@Serializable
data class FileUpdateRequest(
    @SerialName("sha") val sha: String,
    @SerialName("content") val content: String,
    @SerialName("message") val message: String,
    @SerialName("branch") val branch: String
)

@Serializable
data class FileUpdateResponse(val content: Content)

@Serializable
data class Content(
    @SerialName("name") val name: String,
    @SerialName("path") val path: String,
    @SerialName("url") val url: String
)

@Serializable
data class ReleaseResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("html_url") val url: String,
    @SerialName("tag_name") val tag: String,
    @SerialName("target_commitish") val branch: String,
    @SerialName("prerelease") val isPreRelease: Boolean,
) {
    val isStable: Boolean get() = isPreRelease.not()
}

@Serializable
data class ReleaseRequest(
    @SerialName("name") val name: String,
    @SerialName("body") val description: String,
    @SerialName("tag_name") val tag: String,
    @SerialName("target_commitish") val branch: String,
    @SerialName("prerelease") val isPreRelease: Boolean,
    @SerialName("generate_release_notes") val generateReleaseNotes: Boolean,
)

@Serializable
data class ReferenceResponse(
    @SerialName("ref") val ref: String,
    @SerialName("node_id") val nodeId: String,
    @SerialName("url") val url: String,
    @SerialName("object") val objectt: Object,
)

@Serializable
data class ReferenceRequest(
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

    val client = OkHttpClient.Builder()
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
        val token = System.getenv("GITHUB_TOKEN") ?: throw TokenNotFound()

        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/vnd.github+json")
            .addHeader("Authorization", "Bearer $token")
            .addHeader("X-GitHub-Api-Version", "2022-11-28").build()
        return chain.proceed(request)
    }
}