plugins {
    alias(libs.plugins.plrun.android.library)
    alias(libs.plugins.plrun.android.room)

}

android {
    namespace = "id.slava.nt.core.database"
}

dependencies {

    implementation(libs.org.mongodb.bson)
    implementation(projects.core.domain)
}