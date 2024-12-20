plugins {
    alias(libs.plugins.plrun.android.library)
    alias(libs.plugins.plrun.android.junit5)
}

android {
    namespace = "id.slava.nt.core.androidtest"
}

dependencies {

    implementation(projects.auth.data)
    implementation(projects.core.domain)
    api(projects.core.test)

    implementation(libs.ktor.client.mock)
    implementation(libs.bundles.ktor)
    implementation(libs.coroutines.test)
}