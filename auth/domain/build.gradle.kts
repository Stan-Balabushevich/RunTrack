plugins {
    alias(libs.plugins.plrun.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}