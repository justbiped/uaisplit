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
            val releaseType = ReleaseType.valueOf(args.first().uppercase())
            runBlocking {
                cutRelease(releaseType)
            }
        }
    }
}