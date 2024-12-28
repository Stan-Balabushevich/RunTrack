plugins {
    alias(libs.plugins.plrun.android.library)
    alias(libs.plugins.plrun.jvm.ktor)
}

android {
    namespace = "id.slava.nt.run.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(libs.bundles.koin)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.auth)

}