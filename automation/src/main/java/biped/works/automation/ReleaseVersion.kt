package biped.works.automation

interface ReleaseVersion {
    val version: Version
    val previousVersion: Version
    val nextVersion: Version
    val sourceBranch: String
    val targetBranch: String
    val bumpBranch: String
}

fun releaseVersion(previousVersion: Version) = object : ReleaseVersion {
    override val previousVersion: Version get() = previousVersion
    override val version: Version get() = previousVersion
    override val nextVersion: Version get() = version

    override val sourceBranch: String get() = DEFAULT_BRANCH
    override val targetBranch: String get() = ""
    override val bumpBranch = ""
}

data class MajorRelease(
    private val release: ReleaseVersion
) : ReleaseVersion by release {
    override val version: Version = previousVersion.incrementMajor()
    override val nextVersion: Version = previousVersion.incrementMajor(2)
    override val targetBranch: String = "stable/${version.dashName}"
    override val bumpBranch = "version-bump-${nextVersion.dashName}"
}

data class MinorRelease(
    private val release: ReleaseVersion
) : ReleaseVersion by release {
    override val version: Version = previousVersion.incrementMinor()
    override val nextVersion: Version = previousVersion.incrementMinor(2)

    override val targetBranch: String = "stable/${version.dashName}"
    override val bumpBranch = "version-bump-${nextVersion.dashName}"
}

data class PatchRelease(
    private val release: ReleaseVersion
) : ReleaseVersion by release {
    override val version: Version = previousVersion.incrementPatch()
    override val nextVersion: Version = version

    override val sourceBranch: String = "stable/${previousVersion.dashName}"
    override val targetBranch: String = "patch/${version.dashName}"
    override val bumpBranch: String = targetBranch
}