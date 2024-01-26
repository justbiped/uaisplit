package biped.works.plugins.github

import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.konan.file.File

open class CommitTask : Exec() {
    private val rootDir = project.rootDir
    private val versionsPath = "$rootDir/version.properties"
    private val versionRegex = Regex(
        "version.major=(?<major>[0-9]+)\\n" +
                "version.minor=(?<minor>[0-9]+)\\n" +
                "version.patch=(?<patch>[0-9]+)"
    )

    @TaskAction
    override fun exec() {
        val version = bumpAppVersion()
        commandLine("sh", "$rootDir/bla.sh", version.toString())
        super.exec()
    }

    private fun bumpAppVersion() {
        val currentVersion = redCurrentVersion()
        File(versionsPath).writeLines(currentVersion.incrementMinor().inLines())
    }

    private fun redCurrentVersion(): Version {
        val fileContent = File(versionsPath).readStrings().joinToString("\n")
        val findings = versionRegex.find(fileContent) ?: throw Exception("Can't parse versions")

        val (major, minor, patch) = findings.destructured
        return Version(major.toInt(), minor.toInt(), patch.toInt())
    }
}

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