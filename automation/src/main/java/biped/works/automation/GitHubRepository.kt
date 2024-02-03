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

    suspend fun listTags() {
        val response = gitHubApi.listTags()
        print(response)
    }

    suspend fun createTag(tag: Tag): Reference {
        val tagObject = gitHubApi.createTagObject(tag)
        val reference = ReferenceRequest("refs/tags/${tag.tag}", sha = tagObject.sha)
        return gitHubApi.createReference(reference)
    }

    suspend fun createReference(reference: ReferenceRequest): Reference {
        return gitHubApi.createReference(reference)
    }

    suspend fun getCurerntStable(): ReleaseBrief {
        return gitHubApi.getLastReleases().first { !it.preRelease.not() }
    }

    suspend fun createPullRequest(pullRequest: PullRequest) {
        val response = gitHubApi.createPullRequest(pullRequest)
        print(response)
    }

    suspend fun createRelease(release: Release) {
        val bla=  gitHubApi.createRelease(release)
        print(bla)
    }
}

class TagRepository(private val gitHubApi: GitHubApi) {
    suspend fun createTag(tag: Tag): Reference {
        val tagObject = gitHubApi.createTagObject(tag)
        val reference = ReferenceRequest("refs/tags/${tag.tag}", sha = tagObject.sha)
        return gitHubApi.createReference(reference)
    }
}