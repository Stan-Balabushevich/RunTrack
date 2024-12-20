pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "plrun"
include(":app")
include(":auth:data")
include(":auth:domain")
include(":auth:presentation")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
include(":core:data")
include(":core:database")
include(":run:data")
include(":core:domain")
include(":run:domain")
include(":run:presentation")
include(":run:location")
include(":run:network")
include(":core:notification")
include(":analitycs:data")
include(":analitycs:domain")
include(":analitycs:presentation")
include(":analitycs:analitycs_feature")
include(":wear:app")
include(":wear:run:data")
include(":wear:run:domain")
include(":wear:run:presentation")
include(":core:presentation:designsystem_wear")
include(":core:connectivity:domain")
include(":core:connectivity:data")
include(":core:test")
include(":core:androidtest")
