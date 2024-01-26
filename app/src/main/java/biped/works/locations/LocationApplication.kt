package biped.works.locations

import android.app.Application
import biped.works.automation.CutRelease
import biped.works.automation.RefRepository
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

        val gitHubApi = createGitHubApi()
        job.launch {
            CutRelease(
                RefRepository(gitHubApi),
            ).read()
        }
    }
}