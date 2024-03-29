plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.ksp)
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
    ksp(Dependencies.Room.compiler)

    implementation(Dependencies.Hilt.core)
    ksp(Dependencies.Hilt.compiler)
}