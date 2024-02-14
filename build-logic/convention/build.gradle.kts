plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

group = "com.devid_academy.buildlogic"

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "conventionplugin.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "conventionplugin.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}




