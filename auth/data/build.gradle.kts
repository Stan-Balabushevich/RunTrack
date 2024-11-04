plugins {
    alias(libs.plugins.plrun.android.library)
    alias(libs.plugins.plrun.jvm.ktor)
}

android {
    namespace = "id.slava.nt.auth.data"
}

dependencies {

    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    //Koin
    implementation(libs.bundles.koin)
}