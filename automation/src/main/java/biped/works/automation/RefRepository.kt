package biped.works.automation

class RefRepository(private val gitHubApi: GitHubApi) {

    suspend fun getVersionFile(): GitHubFile {
        return gitHubApi.readVersionsVile()
    }

    suspend fun updateVersionFile(file: FileUpdate){
        gitHubApi.updateVersionFile(file)
    }

    suspend fun getObject(branch: String): Object {
        return gitHubApi.listRefs()
            .first { it.ref == "refs/heads/$branch" }
            .objectt
    }
}

class TagRepository(private val gitHubApi: GitHubApi) {
    suspend fun createTag(tag: Tag): Reference {
        val tagObject = gitHubApi.createTagObject(tag)
        val reference = RefRequest("refs/tags/${tag.tag}", sha = tagObject.sha)
        return gitHubApi.createTagReference(reference)
    }
}

class ReleaseRepository(private val gitHubApi: GitHubApi) {
    suspend fun createRelease(release: Release): ReleaseUrl {
        return gitHubApi.createRelease(release)
    }
}