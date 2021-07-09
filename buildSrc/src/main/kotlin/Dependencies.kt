// @formatter:off

object Versions {
    const val kotlin = "1.5.0"
    const val navigation = "2.3.5"
    const val lifecycle = "2.3.1"
    const val retrofit = "2.6.1"
    const val hilt = "2.35.1"
    const val room = "2.3.0"
    const val dagger = "2.37"
}

object Dependencies {
    object Androidx {
        const val core = "androidx.core:core-ktx:1.6.0"
        const val appCompat = "androidx.appcompat:appcompat:1.3.0"
        const val materialDesign = "com.google.android.material:material:1.4.0"
        const val activity = "androidx.activity:activity-ktx:1.2.3"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.5"

        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

        const val inAppBilling = "com.android.billingclient:billing-ktx:4.0.0"

        const val roomRuntime= "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler ="androidx.room:room-compiler:${Versions.room}"
        const val roomKtx =  "androidx.room:room-ktx:${Versions.room}"
    }

    object Kotlin {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:28.0.1"
        const val messaging = "com.google.firebase:firebase-messaging"
        const val analytics =  "com.google.firebase:firebase-analytics"
        const val auth = "com.google.firebase:firebase-auth-ktx:19.3.2"
        const val playServicesAuth = "com.google.android.gms:play-services-auth:18.1.0"
    }

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    object Test {

        private const val robolectricVersion = "4.5.1"
        private const val mockkVersion = "1.12.0"

        const val jUnit = "junit:junit:4.13"
        const val mockitoInline = "org.mockito:mockito-inline:2.28.2"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
        const val assertJ = "org.assertj:assertj-core:3.20.2"
        const val mockk = "io.mockk:mockk:$mockkVersion"
        const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0"

        const val robolectric = "org.robolectric:robolectric:${robolectricVersion}"
        const val robolectricAnnotations = "org.robolectric:annotations:${robolectricVersion}"

        const val androidxCore = "androidx.test:core-ktx:1.4.0"
        const val archCore = "androidx.arch.core:core-testing:2.1.0"
        const val androidxJunit = "androidx.test.ext:junit-ktx:1.1.3"
        const val rules = "androidx.test:rules:1.4.0"
        const val runner = "androidx.test:runner:1.4.0"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        const val fragment = "androidx.fragment:fragment-testing:1.3.5"
        const val navigation = "androidx.navigation:navigation-testing:${Versions.navigation}"
    }

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val httpLogging = "com.squareup.okhttp3:logging-interceptor:3.8.0"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val coil = "io.coil-kt:coil:1.2.2"
}

object Plugins {

    const val dependenciesUpdate = "com.github.ben-manes.versions"

    object Kotlin {
        const val android = "kotlin-android"
        const val androidExtensions = "kotlin-android-extensions"
        const val kapt: String = "kotlin-kapt"
    }

    object Android {
        const val googleServices = "com.google.gms.google-services"
        const val application = "com.android.application"
        const val library = "com.android.library"
        const val hilt = "dagger.hilt.android.plugin"
    }
}

object Path {
    const val androidGradle = "com.android.tools.build:gradle:4.2.1"
    const val playServices = "com.google.gms:google-services:4.3.8"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val dependenciesUpdate = "com.github.ben-manes:gradle-versions-plugin:0.38.0"
}