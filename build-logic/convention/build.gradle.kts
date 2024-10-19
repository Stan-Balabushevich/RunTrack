plugins {
    `kotlin-dsl`
}

group = "id.slava.nt.plrun.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "plrun.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}