// @formatter:off

object Versions {
    const val kotlin = "1.7.0"
    const val lifecycle = "2.4.1"
    const val retrofit = "2.9.0"
    const val hilt = "2.42"
    const val room = "2.3.0"
    const val okHttp = "4.10.0"
}

object Dependencies {
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val serializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    const val httpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val coil = "io.coil-kt:coil:2.1.0"

    object Android {
        object Fragment {
            private const val version = "1.5.1"
            const val fragment = "androidx.fragment:fragment-ktx:$version"
            const val test = "androidx.fragment:fragment-testing:$version"
        }

        const val core = "androidx.core:core-ktx:1.8.0"
        const val appCompat = "androidx.appcompat:appcompat:1.5.0"
        const val materialDesign = "com.google.android.material:material:1.6.1"
        const val activity = "androidx.activity:activity-ktx:1.5.1"


        const val lifecycleLiveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Kotlin {
        const val reflection = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0"
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
        private const val mockkVersion = "1.12.4"

        const val jUnit = "junit:junit:4.13"
        const val assertJ = "org.assertj:assertj-core:3.20.2"
        const val mockk = "io.mockk:mockk:$mockkVersion"
        const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0"
        const val mockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"

        const val robolectric = "org.robolectric:robolectric:${robolectricVersion}"
        const val robolectricAnnotations = "org.robolectric:annotations:${robolectricVersion}"

        const val androidxCore = "androidx.test:core-ktx:1.4.0"
        const val archCore = "androidx.arch.core:core-testing:2.1.0"
        const val androidxJunit = "androidx.test.ext:junit-ktx:1.1.3"
        const val hilt = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
        const val rules = "androidx.test:rules:1.4.0"
        const val runner = "androidx.test:runner:1.4.0"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        const val espressoContrib = "com.android.support.test.espresso:espresso-contrib:3.4.0"
        const val uiAutomator = "androidx.test.uiautomator:uiautomator:2.2.0"
    }

    object Compose {
        private const val version = "1.2.0"
        const val ui = "androidx.compose.ui:ui:$version"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val material = "androidx.compose.material:material:$version"
        const val icons = "androidx.compose.material:material-icons-core:$version"
        const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
        const val layout = "androidx.compose.foundation:foundation-layout:$version"
        const val animation = "androidx.compose.animation:animation:$version"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"

        const val pager = "com.google.accompanist:accompanist-pager:0.25.1"

        const val testManifest = "androidx.compose.ui:ui-test-manifest:$version"
        const val test = "androidx.compose.ui:ui-test-junit4:$version"
    }

    object Navigator {
        private const val version = "2.5.1"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ui = "androidx.navigation:navigation-ui-ktx:$version"
        const val testing = "androidx.navigation:navigation-testing:$version"
        const val classPath = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        const val plugin = "androidx.navigation.safeargs.kotlin"
    }
}

object Plugins {
    const val dependenciesUpdate = "com.github.ben-manes.versions"

    object Kotlin {
        const val serialization = "kotlinx-serialization"
        const val android = "kotlin-android"
        const val kapt: String = "kotlin-kapt"
        const val parcelize = "kotlin-parcelize"
    }

    object Android {
        const val application = "com.android.application"
        const val library = "com.android.library"
        const val test: String = "com.android.test"
        const val googleServices = "com.google.gms.google-services"
        const val hilt = "dagger.hilt.android.plugin"
    }
}

object Path {
    const val androidGradle = "com.android.tools.build:gradle:7.2.1"
    const val playServices = "com.google.gms:google-services:4.3.8"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val dependenciesUpdate = "com.github.ben-manes:gradle-versions-plugin:0.42.0"
}