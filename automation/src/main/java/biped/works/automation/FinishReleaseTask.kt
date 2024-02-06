package biped.works.automation

var defaultBranch = ""

class FinishReleaseTask {
    companion object {
        val repository = GitHubRepository(createGitHubApi())
        val finishRelease = FinishRelease(repository)

        @JvmStatic
        fun main(args: Array<String>) {
            try {
                defaultBranch = args[0]
                finishRelease()
            }catch (error: Error){
                print(error.message)
            }

        }
    }
}