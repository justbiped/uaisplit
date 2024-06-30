plugins {
    apply(libs.plugins.java)
    apply(libs.plugins.kotlin.jvm)
    apply(libs.plugins.kotlin.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.coroutine.core)

    implementation(libs.http)
    implementation(libs.http.retrofit)

    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.serialization.converter)

    testImplementation(libs.test.junit)
}

tasks.register("minorRelease", JavaExec::class) {
    group = "biped.works"
    mainClass.set("biped.works.automation.CreateReleaseTask")
    args = listOf("master","minor")
    classpath = sourceSets["main"].runtimeClasspath

    debugOptions {
        isEnabled = true
    }
}

tasks.register("finishRelease", JavaExec::class) {
    group = "biped.works"
    mainClass.set("biped.works.automation.FinishReleaseTask")
    args = listOf("master")
    classpath = sourceSets["main"].runtimeClasspath

    debugOptions {
        isEnabled = true
    }
}