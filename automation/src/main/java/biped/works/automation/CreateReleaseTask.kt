package biped.works.automation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking

class CreateReleaseTask {

    companion object {
        val repository: GitHubRepository = GitHubRepository(createGitHubApi())
        val cutRelease = CutRelease(repository)
        val job = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        @JvmStatic
        fun main(args: Array<String> = arrayOf("minor")) {
            defaultBranch = args[0]
            val type = args[1].uppercase()

            val releaseType = ReleaseType.valueOf(type)
            runBlocking {
                cutRelease(releaseType)
            }
        }
    }
}