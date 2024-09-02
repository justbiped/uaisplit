import biped.works.plugins.UNIT_TEST_VARIANT
import biped.works.plugins.hasTestPlugin
import biped.works.plugins.reportsConfig
import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

apply<VersionsPlugin>()
apply<ConfigPlugin>()

plugins {
    alias(libs.plugins.android.playServices) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.dependencyUpdates)
    apply(libs.plugins.kotlin.kover)
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
        "koverXMLReportUnitTest"
    )
    dependsOn("prepareKoverDependencies")
}

tasks.create<Exec>("coverageHtmlReport") {
    commandLine(
        "./gradlew",
        "koverHTMLReportUnitTest"
    )
    dependsOn("prepareKoverDependencies")
}

project.tasks.create("prepareKoverDependencies") {
    val includeSubprojects = System.getenv("INCLUDE_SUBPROJECT").toBoolean()
    if (includeSubprojects) {
        val testedDependencies = project.subprojects
            .filter { it.hasTestPlugin }
            .map { it.dependency }

        dependencies {
            testedDependencies.forEach { dependency ->
                kover(project(dependency))
            }
        }

        println("Tracking unit test coverage for modules: $testedDependencies")
    }
}

kover {
    currentProject {
        createVariant(UNIT_TEST_VARIANT) {}
    }
    reports(reportsConfig)
}