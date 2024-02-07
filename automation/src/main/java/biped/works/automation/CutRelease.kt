package biped.works.automation

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

enum class ReleaseType {
    //MAJOR,
    MINOR,
    PATCH
}

class CutRelease(private val repository: GitHubRepository) {

    operator fun invoke(type: ReleaseType) {
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

    private fun createRelease(releaseVersion: ReleaseVersion) {
        println("Creating release ${releaseVersion.version.name}")
        val targetBranch = releaseVersion.targetBranch
        val release = ReleaseRequest(
            releaseVersion.version.name,
            description = "",
            branch = targetBranch,
            tag = "${releaseVersion.version.tag}-beta",
            isPreRelease = true,
            generateReleaseNotes = true
        )
        val response = repository.createRelease(release)
        response?.also { println("Release created ${response.url}") }
    }

    private fun createReleaseBranch(release: ReleaseVersion, source: Object) {
        println("Creating stable branch ${release.targetBranch} from $defaultBranch")
        val stableRefRequest = ReferenceRequest("refs/heads/${release.targetBranch}", source.sha)
        repository.createReference(stableRefRequest)
    }

    private fun getCurrentVersion(): Version {
        println("Getting the current stable version")
        val stableRelease = repository.getCurrentStable()
        val (major, minor, patch) = stableRelease.tag.replace("v", "").split(".")
        return Version(major.toInt(), minor.toInt(), patch.toInt())
    }

    private fun createBumpVersionBranch(release: ReleaseVersion, source: Object) {
        if (release is PatchRelease) return
        println("Creating bump version branch")
        val versionRefRequest = ReferenceRequest("refs/heads/${release.bumpBranch}", source.sha)
        repository.createReference(versionRefRequest)

    }

    private fun updateVersionFile(release: ReleaseVersion) {
        println("Updating version file to ${release.nextVersion}")
        val gitHubFile = repository.getVersionFile(release.bumpBranch)
        val fileContent = release.nextVersion.fileContent.encodeToBase64()

        val fileUpdate = FileUpdateRequest(
            sha = gitHubFile.sha,
            content = fileContent,
            message = "Bumping version to ${release.nextVersion}",
            branch = release.bumpBranch
        )
        repository.updateVersionFile(fileUpdate)
    }

    private fun openBumpVersionPR(release: ReleaseVersion) {
        if (release is PatchRelease) return

        println("Creating version bump PR")
        val pullRequest = PullRequest(
            "Bumping version to ${release.nextVersion}",
            "",
            release.bumpBranch,
            release.sourceBranch
        )
        val pullResponse = repository.createPullRequest(pullRequest)
        pullResponse?.also { println("Bump version pull request created: ${pullResponse.url}") }
    }
}

@OptIn(ExperimentalEncodingApi::class)
fun String.encodeToBase64(): String {
    return Base64.encode(toByteArray())
}