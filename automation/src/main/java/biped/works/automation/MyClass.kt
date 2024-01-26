package biped.works.automation

class CreateRelease(
    private val releaseRepository: ReleaseRepository
) {
    suspend operator fun invoke(release: Release) {
        try {
            val releaseUrl = releaseRepository.createRelease(release)
            print(releaseUrl)
        } catch (error: Throwable) {
            error.printStackTrace()
        }
    }
}