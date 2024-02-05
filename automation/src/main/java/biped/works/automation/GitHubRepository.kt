package biped.works.automation

class GitHubRepository(private val gitHubApi: GitHubApi) {

    suspend fun getVersionFile(branch: String): GitHubFile {
        return gitHubApi.readVersionsVile(branch)
    }

    suspend fun updateVersionFile(file: FileUpdate) {
        gitHubApi.updateVersionFile(file)
    }

    suspend fun getBranch(branch: String): Object {
        return gitHubApi.listRefs()
            .first { it.ref == "refs/heads/$branch" }
            .objectt
    }

    suspend fun createTag(tag: Tag): Reference {
        val tagObject = gitHubApi.createTagObject(tag)
        val reference = ReferenceRequest("refs/tags/${tag.tag}", sha = tagObject.sha)
        return gitHubApi.createReference(reference)
    }

    suspend fun createReference(reference: ReferenceRequest): Reference {
        return gitHubApi.createReference(reference)
    }

    suspend fun getCurrentStable(): ReleaseBrief {
        return gitHubApi.getLastReleases().first { it.isPreRelease.not() }
    }

    suspend fun getLatestRelease(): ReleaseBrief {
        return gitHubApi.getLastReleases(perPage = 1).first()
    }

    suspend fun updateRelease(id: String, release: Release) {
        gitHubApi.updateRelease(id, release)
    }

    suspend fun createPullRequest(pullRequest: PullRequest) {
        val response = gitHubApi.createPullRequest(pullRequest)
        print(response)
    }

    suspend fun createRelease(release: Release) {
        val bla = gitHubApi.createRelease(release)
        print(bla)
    }
}