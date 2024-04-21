plugins {
    apply(libs.plugins.android.application)
    apply(libs.plugins.hilt)
    apply(libs.plugins.kotlin.serialization)
    apply(libs.plugins.ksp)
    apply(libs.plugins.compose)
}

android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"

        namespace = "biped.works.locations"
    }

    signingConfigs {
        create("production") {
            storeFile = File("${rootProject.rootDir}/location_key_prod")
            storePassword = "1a]IKErY0g]!\\K)(v"
            keyAlias = "locaitons"
            keyPassword = "1a]IKErY0g]!\\K)(v"
        }
    }

    buildTypes {
        local {
            applicationIdSuffix = ".local"
        }

        production {
            signingConfig = signingConfigs.getByName("production")
        }
    }
}

dependencies {
    implementation(libs.android.core)
    implementation(libs.coroutine.android)

    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewModel)

    implementation(libs.http)
    implementation(libs.http.retrofit)
    implementation(libs.http.logging)

    implementation(libs.serialization.core)
    implementation(libs.serialization.converter)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    implementation(project(":automation"))
    implementation(project(":features:statement"))
    implementation(project(":features:profile"))
    implementation(project(":features:settings"))
    implementation(project(":features:user"))

    implementation(project(":foundation:core"))
    implementation(project(":foundation:theme"))
    testImplementation(project(":foundation:test"))

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    kspTest(libs.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)
}