plugins {
    alias(libs.plugins.plrun.jvm.library)
}
dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(projects.core.domain)
}