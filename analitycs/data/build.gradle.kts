plugins {
    alias(libs.plugins.plrun.android.library)
    alias(libs.plugins.plrun.android.room)
}

android {
    namespace = "id.slava.nt.analitycs.data"
}

dependencies {

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.koin)

    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.analitycs.domain)
}
