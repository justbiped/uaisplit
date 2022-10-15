plugins {
    id(Plugins.library)
}

android {
    namespace = "biped.works.tosplit.statement"

}

dependencies {

    implementation(Dependencies.Kotlin.core)
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}