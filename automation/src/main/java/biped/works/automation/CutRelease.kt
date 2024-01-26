package biped.works.automation

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class CutRelease(private val repository: RefRepository) {

    suspend fun read() {
        val gitHubFile = repository.getVersionFile()
        val version = Version.parse(gitHubFile.decodedContent)
        val updatedVersion = version.incrementMinor()
        val versionUpdateContent = updatedVersion.fileContent.encodeToBase64()

        val fileUpdate = FileUpdate(
            sha = gitHubFile.sha,
            content = versionUpdateContent,
            message = "Bumping version to $updatedVersion",
            branch = "version-bump"
        )

        repository.updateVersionFile(fileUpdate)
        print(version)
    }
}

@OptIn(ExperimentalEncodingApi::class)
fun String.encodeToBase64(): String {
    return Base64.encode(toByteArray())
}