package biped.works.automation

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okio.ByteString.Companion.decodeBase64
import retrofit2.Response
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
    suspend fun listRefs(): List<Reference>

    @POST("/repos/justbiped/uaisplit/git/tags")
    suspend fun createTagObject(@Body tag: Tag): TagObject

    @POST("/repos/justbiped/uaisplit/git/refs")
    suspend fun createReference(@Body request: ReferenceRequest): Reference

    @GET("/repos/TheAthletic/android/git/refs/tags")
    suspend fun listTags(): Response<ResponseBody>

    @GET("/repos/justbiped/uaisplit/contents/version.properties")
    suspend fun readVersionsVile(@Query("ref") ref: String = "heads/version-bump"): GitHubFile

    @PUT("/repos/justbiped/uaisplit/contents/version.properties")
    suspend fun updateVersionFile(@Body file: FileUpdate): Response<ResponseBody>

    @POST("/repos/justbiped/uaisplit/pulls")
    suspend fun createPullRequest(@Body pullRequest: PullRequest): Response<ResponseBody>

    @GET("/repos/justbiped/uaisplit/releases")
    suspend fun getLastReleases(@Query("page") page: Int = 1, @Query("perPage") perPage: Int = 3): List<ReleaseBrief>

    @POST("/repos/justbiped/uaisplit/releases")
    suspend fun createRelease(@Body release: Release): ReleaseBrief

    @PATCH("/repos/justbiped/uaisplit/releases/{id}")
    suspend fun updateRelease(@Path("id") id: String, @Body release: Release): ReleaseBrief
}

@Serializable
data class PullRequest(
    @SerialName("title") val title: String,
    @SerialName("body") val body: String,
    @SerialName("head") val head: String,
    @SerialName("base") val base: String,
)

@Serializable
data class GitHubFile(
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
data class FileUpdate(
    @SerialName("sha") val sha: String,
    @SerialName("content") val content: String,
    @SerialName("message") val message: String,
    @SerialName("branch") val branch: String
)

@Serializable
data class ReleaseBrief(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
    @SerialName("tag_name") val tag: String,
    @SerialName("target_commitish") val branch: String,
    @SerialName("prerelease") val isPreRelease: Boolean,
) {
    val isStable: Boolean get() = isPreRelease.not()
}

@Serializable
data class Release(
    @SerialName("name") val name: String,
    @SerialName("body") val description: String,
    @SerialName("tag_name") val tag: String,
    @SerialName("target_commitish") val branch: String,
    @SerialName("prerelease") val isPreRelease: Boolean,
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
        val token = System.getProperty("github.token") ?: System.getenv("GIT_HUB_TOKEN") ?: throw Exception(
            "Unable to find the authorization token for Git Hub\n" +
                    "add github.token=<Personal Access Token here> in your ~/.gradle/gradle.properties or export GIT_HUB_TOKEN evn var"
        )

        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/vnd.github+json")
            .addHeader("Authorization", "Bearer $token")
            .addHeader("X-GitHub-Api-Version", "2022-11-28").build()
        return chain.proceed(request)
    }
}