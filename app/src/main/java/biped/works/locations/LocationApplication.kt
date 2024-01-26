package biped.works.locations

import android.app.Application
import biped.works.automation.CreateRelease
import biped.works.automation.Release
import biped.works.automation.ReleaseRepository
import biped.works.automation.createGitHubApi
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@HiltAndroidApp
class LocationApplication : Application() {

    val job = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    override fun onCreate() {
        super.onCreate()
        val release = Release(
            name = "0.0.1 Beta",
            description = "Generated automatically",
            tag = "0.0.1-beta",
            sourceBranch = "master",
            draft = false,
            preRelease = true,
            generateReleaseNotes = true
        )

        val gitHubApi = createGitHubApi()
        job.launch {
            CreateRelease(
                ReleaseRepository(gitHubApi),
            ).invoke(release)
        }
    }
}