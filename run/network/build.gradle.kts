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
}