import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

apply<VersionsPlugin>()
apply<ConfigPlugin>()

plugins {
    alias(libs.plugins.android.playServices) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.kover) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.dependencyUpdates)
    id("com.github.nbaztec.coveralls-jacoco") version "1.2.20"
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "report/dependencies"
    reportfileName = "available-versions"

    rejectVersionIf {
        candidate.version.contains("alpha") ||
                candidate.version.contains("beta") ||
                candidate.version.contains("SNAPSHOT")
    }
}

tasks.create<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

tasks.create<Exec>("coverageXmlReport") {
    commandLine(
        "./gradlew",
        ":app:koverXMLReportLocal"
    )
}

tasks.create<Exec>("coverageHtmlReport") {
    commandLine(
        "./gradlew",
        ":app:koverHTMLReportLocal"
    )
}

coverallsJacoco {
    val sourceSets = subprojects
        .filter { project -> project.buildFile.exists() }
        .map { project -> "${project.projectDir.path}/src/main/java" }
        .map { File(it) }

    reportSourceSets = sourceSets
    reportPath = "${rootDir}/app/build/reports/kover/reportLocal.xml"
}