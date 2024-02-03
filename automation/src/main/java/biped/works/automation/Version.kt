package biped.works.automation

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int
) {
    val fileContent = "version.major=$major\nversion.minor=$minor\nversion.patch=$patch"
    val name = "$major.$minor.$patch"
    val tag = "v$name"
    val dashName = "$major-$minor"

    fun incrementMajor(value: Int = 1) = copy(major = major + value, minor = 0, patch = 0)
    fun incrementMinor(value: Int = 1) = copy(minor = minor + value, patch = 0)
    fun incrementPatch(value: Int = 1) = copy(patch = patch + value)

    override fun toString() = name

    companion object {
        fun parse(content: String): Version {
            val versionRegex = Regex(
                "version.major=(?<major>[0-9]+)\\\\n" +
                        "version.minor=(?<minor>[0-9]+)\\\\" +
                        "nversion.patch=(?<patch>[0-9]+)"
            )
            val findings = versionRegex.find(content) ?: throw Exception("Can't parse versions")
            val (major, minor, patch) = findings.destructured
            return Version(major.toInt(), minor.toInt(), patch.toInt())
        }
    }
}