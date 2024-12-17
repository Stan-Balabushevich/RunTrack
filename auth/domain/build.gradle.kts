plugins {
    alias(libs.plugins.plrun.jvm.library)
    alias(libs.plugins.plrun.jvm.junit5)
}

dependencies {
    implementation(projects.core.domain)
}