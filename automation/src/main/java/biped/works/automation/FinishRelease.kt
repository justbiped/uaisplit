package biped.works.automation

class FinishRelease(private val repository: GitHubRepository) {

    suspend operator fun invoke() {
        val latestRelease = repository.getLatestRelease()
        if (latestRelease.isStable) {
            print("The release ${latestRelease.name} is marked as stable, which is finished already")
            return
        }

        val release = Release(
            name = latestRelease.name,
            description = "",
            tag = latestRelease.tag,
            branch = latestRelease.branch,
            isPreRelease = false,
            generateReleaseNotes = true
        )

        repository.updateRelease(latestRelease.id.toString(), release)

        val pullRequest = PullRequest(
            title = "Merge back ${latestRelease.name}",
            body = "",
            head = latestRelease.branch,
            base = DEFAULT_BRANCH
        )

        repository.createPullRequest(pullRequest)
    }
}