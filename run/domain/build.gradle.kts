plugins {
    alias(libs.plugins.plrun.jvm.library)
    alias(libs.plugins.plrun.jvm.junit5)
}
dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(projects.core.domain)
    implementation(projects.core.connectivity.domain)
    testImplementation(projects.core.test)
//    testImplementation(libs.coroutines.test)
//    testImplementation(libs.turbine)
}