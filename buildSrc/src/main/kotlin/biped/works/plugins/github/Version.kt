package biped.works.plugins.github

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int
) {
    fun incrementMinor(value: Int = 1) = copy(minor = minor + value)

    override fun toString() = "$major.$minor.$patch"

    fun inLines(): List<String> {
        return "version.major=$major\nversion.minor=$minor\nversion.patch=$patch".split("\n")
    }
}