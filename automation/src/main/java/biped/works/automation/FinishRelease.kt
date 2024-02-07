package biped.works.automation

class FinishRelease(private val repository: GitHubRepository) {

    operator fun invoke() {
        println("Looking for the last pre-release")
        val latestRelease = repository.getLatestRelease()
        if (latestRelease.isStable) {
            throw Exception("The release ${latestRelease.name} is marked as stable, which is finished already")
        }

        println("Marking the release ${latestRelease.name} as stable")
        val release = ReleaseRequest(
            name = latestRelease.name,
            description = "",
            tag = "v${latestRelease.name}",
            branch = latestRelease.branch,
            isPreRelease = false,
            generateReleaseNotes = true
        )
        repository.updateRelease(latestRelease.id.toString(), release)

        println("Creating the merge-back PR from ${latestRelease.name} to $defaultBranch")
        val pullRequest = PullRequest(
            title = "Merge back ${latestRelease.name}",
            body = " ",
            head = latestRelease.branch,
            base = defaultBranch
        )
        repository.createPullRequest(pullRequest)
    }
}
