package biped.works.automation

import kotlinx.coroutines.runBlocking

var defaultBranch = ""
class FinishReleaseReleaseTask {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val repository = GitHubRepository(createGitHubApi())
            val finishRelease = FinishRelease(repository)
            defaultBranch = args[0]

            runBlocking {
                finishRelease()
            }
        }
    }
}