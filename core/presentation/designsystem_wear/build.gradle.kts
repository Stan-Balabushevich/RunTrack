plugins {
    alias(libs.plugins.plrun.android.library.compose)
}

android {
    namespace = "id.slava.nt.core.presentation.designsystem_wear"

    defaultConfig {
        minSdk = 30
    }
}

dependencies {
    api(projects.core.presentation.designsystem)
    implementation(libs.androidx.wear.compose.material)
}