plugins {
    alias(libs.plugins.plrun.android.dynamic.feature)
}
android {
    namespace = "id.slava.nt.analitycs.analitycs_feature"
}

dependencies {
    implementation(project(":app"))
    implementation(libs.androidx.navigation.compose)

    api(projects.analitycs.presentation)
    implementation(projects.analitycs.domain)
    implementation(projects.analitycs.data)
    implementation(projects.core.database)
}