plugins {
    alias(libs.plugins.plrun.android.feature.ui)
}

android {
    namespace = "id.slava.nt.auth.presentation"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}