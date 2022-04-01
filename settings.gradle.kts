pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

//    plugins{
//        id("com.android.application" ) version "7.1.2" apply false
//        id("com.android.library") version "7.1.2" apply false
//    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:7.1.2")
            }
        }
    }
}
rootProject.name = "KMMBesida"

include(":androidApp")
include(":shared")