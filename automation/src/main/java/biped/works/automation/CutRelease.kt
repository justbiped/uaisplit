package biped.works.automation

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

enum class ReleaseType {
    //MAJOR,
    MINOR,
    PATCH
}

class CutRelease(private val repository: GitHubRepository) {

    suspend operator fun invoke(type: ReleaseType) {
        val version = getCurrentVersion()
        val baseReleaseVersion = releaseVersionn(previousVersion = version)
        val release = when (type) {
            //  ReleaseType.MAJOR -> MajorRelease(baseReleaseVersion)
            ReleaseType.MINOR -> MinorRelease(baseReleaseVersion)
            ReleaseType.PATCH -> PatchRelease(baseReleaseVersion)
        }

        val source = repository.getBranch(release.sourceBranch)

        try {
            createReleaseBranch(release, source)
            createRelease(release)

            createBumpVersionBranch(release, source)
            updateVersionFile(release)
            openBumpVersionPR(release)
        } catch (error: Error) {
            print(error)
        }
    }

    private suspend fun createRelease(releaseVersion: ReleaseVersion) {
        val targetBranch = releaseVersion.targetBranch
        val release = ReleaseRequest(
            releaseVersion.version.name,
            description = "",
            branch = targetBranch,
            tag = "${releaseVersion.version.tag}-beta",
            isPreRelease = true,
            generateReleaseNotes = true
        )
        repository.createRelease(release)
    }

    private suspend fun createReleaseBranch(release: ReleaseVersion, source: Object) {
        val stableRefRequest = ReferenceRequest("refs/heads/${release.targetBranch}", source.sha)
        repository.createReference(stableRefRequest)
    }

    private suspend fun getCurrentVersion(): Version {
        val stableRelease = repository.getCurrentStable()
        val (major, minor, patch) = stableRelease.tag.replace("v", "").split(".")
        return Version(major.toInt(), minor.toInt(), patch.toInt())
    }

    private suspend fun createBumpVersionBranch(release: ReleaseVersion, source: Object) {
        if (release is PatchRelease) return

        val versionRefRequest = ReferenceRequest("refs/heads/${release.bumpBranch}", source.sha)
        repository.createReference(versionRefRequest)

    }

    private suspend fun updateVersionFile(release: ReleaseVersion) {
        val gitHubFile = repository.getVersionFile(release.bumpBranch)
        val fileContent = release.nextVersion.fileContent.encodeToBase64()

        val fileUpdate = FileUpdate(
            sha = gitHubFile.sha,
            content = fileContent,
            message = "Bumping version to ${release.nextVersion}",
            branch = release.bumpBranch
        )
        repository.updateVersionFile(fileUpdate)
    }

    private suspend fun openBumpVersionPR(release: ReleaseVersion) {
        if (release is PatchRelease) return

        val pullRequest = PullRequest(
            "Bumping version to ${release.nextVersion}",
            "",
            release.bumpBranch,
            release.sourceBranch
        )
        repository.createPullRequest(pullRequest)
    }
}

@OptIn(ExperimentalEncodingApi::class)
fun String.encodeToBase64(): String {
    return Base64.encode(toByteArray())
}