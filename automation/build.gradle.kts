plugins {
    id(Plugins.java_library)
    id(Plugins.kotlin_jvm)
    id(Plugins.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)

    implementation(Dependencies.Coroutines.core)

    implementation(Dependencies.Square.retrofit)
    implementation(Dependencies.Square.okHttp)

    implementation(Dependencies.Serialization.core)
    implementation(Dependencies.Serialization.converter)

    testImplementation(Dependencies.Test.jUnit)
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