plugins {
    alias(libs.plugins.plrun.android.library)
}

android {
    namespace = "id.slava.nt.core.database"
}

dependencies {

    implementation(libs.org.mongodb.bson)
    implementation(projects.core.domain)
}