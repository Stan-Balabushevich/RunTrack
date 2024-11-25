plugins {
    alias(libs.plugins.plrun.android.feature.ui)
}

android {
    namespace = "id.slava.nt.analitycs.presentation"
}

dependencies {

    implementation(projects.analitycs.domain)
}