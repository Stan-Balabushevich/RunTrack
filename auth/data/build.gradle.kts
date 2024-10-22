plugins {
    alias(libs.plugins.plrun.android.library)
}

android {
    namespace = "id.slava.nt.auth.data"
}

dependencies {

    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}