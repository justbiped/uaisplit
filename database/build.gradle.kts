plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "biped.works.database"

    defaultConfig {
        javaCompileOptions.annotationProcessorOptions {
            arguments += mapOf(
                "room.schemaLocation" to "$projectDir/schemas",
                "room.incremental" to "true",
                "room.expandProjection" to "true"
            )
        }
    }
}

dependencies {
    api(Dependencies.Room.runtime)
    implementation(Dependencies.Room.extesions)
    kapt(Dependencies.Room.compiler)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)
}