package biped.works.automation

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int
) {
    val fileContent = "version.major=$major\nversion.minor=$minor\nversion.patch=$patch"

    override fun toString() = "$major.$minor.$patch"

    fun incrementMinor(value: Int = 1) = copy(minor = minor + value)

    fun inLines(): List<String> {
        return fileContent.split("\n")
    }

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