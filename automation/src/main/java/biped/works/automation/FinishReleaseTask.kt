package biped.works.automation

var defaultBranch = ""

class FinishReleaseTask {
    companion object {
        private val repository = GitHubRepository(createGitHubApi())
        private val finishRelease = FinishRelease(repository)

        @JvmStatic
        fun main(args: Array<String>) {
            try {
                defaultBranch = args[0]
                finishRelease()
            } catch (error: Throwable) {
                print(error.message)
            }

        }
    }
}