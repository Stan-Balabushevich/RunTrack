plugins {
    alias(libs.plugins.plrun.android.library)
    alias(libs.plugins.plrun.jvm.ktor)
}

android {
    namespace = "id.slava.nt.core.data"
}

dependencies {

    implementation(libs.timber)
    implementation(projects.core.domain)
    implementation(projects.core.database)
}