package biped.works.automation

class CreateReleaseTask {

    companion object {
        private val repository: GitHubRepository = GitHubRepository(createGitHubApi())
        private val cutRelease = CutRelease(repository)

        @JvmStatic
        fun main(args: Array<String> = arrayOf("minor")) {
            defaultBranch = args[0]
            val type = args[1].uppercase()
            val releaseType = ReleaseType.valueOf(type)

            println("Starting $type release")
            cutRelease(releaseType)
        }
    }
}