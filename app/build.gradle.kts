plugins {
    id(Plugins.application)
    id(Plugins.hilt)
    id(Plugins.serialization)
    id(Plugins.ksp)
    id(Plugins.compose)
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
            buildConfigField("String", "BASE_URL", "\"https://hotmart-mobile-app.herokuapp.com/\"")
        }
    }
}

dependencies {
    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.Android.core)

    implementation(Dependencies.Lifecycle.viewModel)
    implementation(Dependencies.Lifecycle.runtime)

    implementation(Dependencies.Square.retrofit)
    implementation(Dependencies.Square.httpLogging)
    implementation(Dependencies.Square.okHttp)
    implementation(Dependencies.Serialization.converter)
    implementation(Dependencies.Serialization.core)

    implementation(Dependencies.Hilt.core)
    ksp(Dependencies.Hilt.compiler)

    implementation(project(":automation"))
    implementation(project(":features:user"))
    implementation(project(":features:settings"))
    implementation(project(":features:profile"))
    implementation(project(":features:transaction"))

    implementation(project(":foundation:core"))
    implementation(project(":foundation:theme"))
    testImplementation(project(":foundation:test"))

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    kspTest(Dependencies.Hilt.compiler)
    kspAndroidTest(Dependencies.Hilt.compiler)
}
