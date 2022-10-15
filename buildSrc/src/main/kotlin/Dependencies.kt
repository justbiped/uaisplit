// @formatter:off
object Dependencies {
    object Square {
        const val retrofitVersion = "2.9.0"
        const val okHttpVersion = "4.10.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val httpLogging = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val mockServer = "com.squareup.okhttp3:mockwebserver:$okHttpVersion"
    }

    object Android {
        const val core = "androidx.core:core-ktx:1.8.0"
        const val appCompat = "androidx.appcompat:appcompat:1.5.0"
        const val materialDesign = "com.google.android.material:material:1.6.1"
        const val activity = "androidx.activity:activity-ktx:1.5.1"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

        const val datastore = "androidx.datastore:datastore-preferences:1.0.0"

        const val roomVersion = "2.3.0"
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }

    object Kotlin {
        const val version = "1.7.0"
        const val core = "androidx.core:core-ktx:$version"
        const val reflection = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object Serialization {
        const val core = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"
        const val converter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    }

    object Coroutines {
        private const val version = "1.6.4"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:28.0.1"
        const val messaging = "com.google.firebase:firebase-messaging"
        const val analytics = "com.google.firebase:firebase-analytics"
        const val auth = "com.google.firebase:firebase-auth-ktx:19.3.2"
        const val playServicesAuth = "com.google.android.gms:play-services-auth:18.1.0"
    }

    object Test {
        private const val robolectricVersion = "4.8.1"
        private const val mockkVersion = "1.12.7"

        const val jUnit = "junit:junit:4.13"
        const val assertJ = "org.assertj:assertj-core:3.23.1"
        const val mockk = "io.mockk:mockk:$mockkVersion"
        const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"

        const val robolectric = "org.robolectric:robolectric:${robolectricVersion}"
        const val robolectricAnnotations = "org.robolectric:annotations:${robolectricVersion}"

        const val androidxCore = "androidx.test:core-ktx:1.4.0"
        const val archCore = "androidx.arch.core:core-testing:2.1.0"
        const val androidxJunit = "androidx.test.ext:junit-ktx:1.1.3"
        const val rules = "androidx.test:rules:1.4.0"
        const val runner = "androidx.test:runner:1.4.0"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        const val espressoContrib = "com.android.support.test.espresso:espresso-contrib:3.4.0"
        const val uiAutomator = "androidx.test.uiautomator:uiautomator:2.2.0"
    }

    object Fragment {
        private const val version = "1.5.2"
        const val core = "androidx.fragment:fragment-ktx:$version"
        const val testing = "androidx.fragment:fragment-testing:$version"
    }

    object Hilt {
        const val version = "2.44"
        const val core = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
    }

    object Compose {
        private const val version = "1.2.1"
        const val compiler_vesion = "1.2.0"

        const val ui = "androidx.compose.ui:ui:$version"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val material = "androidx.compose.material:material:$version"
        const val icons = "androidx.compose.material:material-icons-core:$version"
        const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
        const val layout = "androidx.compose.foundation:foundation-layout:$version"
        const val animation = "androidx.compose.animation:animation:$version"
        const val activity = "androidx.activity:activity-compose:1.6.0"
        const val pager = "com.google.accompanist:accompanist-pager:0.25.1"

        const val navigation = "androidx.navigation:navigation-compose:2.5.2"
        const val hilt = "androidx.hilt:hilt-navigation-compose:1.0.0"

        const val coil = "io.coil-kt:coil-compose:2.2.0"

        const val testManifest = "androidx.compose.ui:ui-test-manifest:$version"
        const val test = "androidx.compose.ui:ui-test-junit4:$version"
    }

    object Lifecycle {
        private const val version = "2.5.1"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
    }

    object Navigator {
        const val version = "2.5.1"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ui = "androidx.navigation:navigation-ui-ktx:$version"
        const val testing = "androidx.navigation:navigation-testing:$version"
    }
}

object Plugins {
    const val application = "com.android.application"
    const val library = "com.android.library"
    const val kotlin = "org.jetbrains.kotlin.android"
    const val kotlin_android = "kotlin-android"

    const val kapt: String = "kotlin-kapt"
    const val parcelize = "kotlin-parcelize"

    const val serialization = "org.jetbrains.kotlin.plugin.serialization"
    const val serialization_version = Dependencies.Kotlin.version

    const val play_services = "com.google.gms.google-services"
    const val play_services_version = "4.3.8"

    const val safe_args = "androidx.navigation.safeargs.kotlin"
    const val safe_args_version = Dependencies.Navigator.version

    const val hilt = "com.google.dagger.hilt.android"
    const val hilt_version = Dependencies.Hilt.version

    const val dependency_updates = "com.github.ben-manes.versions"
    const val dependency_updates_version = "0.42.0"

    const val compose = "biped.works.plugins.compose"

    const val test = "com.android.test"
}

object Path {
    const val androidGradle = "com.android.tools.build:gradle:7.3.0"
    const val playServices = "com.google.gms:google-services:4.3.8"
    const val dependenciesUpdate = "com.github.ben-manes:gradle-versions-plugin:0.42.0"
}