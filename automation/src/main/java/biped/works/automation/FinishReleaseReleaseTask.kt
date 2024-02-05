package biped.works.automation

import kotlinx.coroutines.runBlocking

class FinishReleaseReleaseTask {

    companion object {
        val repository: GitHubRepository = GitHubRepository(createGitHubApi())
        val finishRelease = FinishRelease(repository)

        @JvmStatic
        fun main(args: Array<String>) {
            runBlocking {
                finishRelease()
            }
        }
    }
}