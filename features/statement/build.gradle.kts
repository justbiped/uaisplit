plugins {
    id(Plugins.library)
    id(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.compose)
}

android {
    namespace = "biped.works.tosplit.statement"
}

dependencies {
    implementation(Dependencies.Kotlin.core)
    implementation(Dependencies.Android.appCompat)

    implementation(project(":core"))
    implementation(project(":theme"))

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}