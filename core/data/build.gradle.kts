plugins {
    alias(libs.plugins.plrun.android.library)
}

android {
    namespace = "id.slava.nt.core.data"
}

dependencies {

    implementation(libs.timber)
    implementation(projects.core.domain)
    implementation(projects.core.database)
}