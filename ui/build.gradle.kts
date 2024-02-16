@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {

    id("conventionplugin.android.library.ui")

    id("androidx.navigation.safeargs.kotlin")

}

android {
    namespace = "com.devid_academy.ui"

    buildFeatures {
        dataBinding = true
    }

}

dependencies {

    implementation(project(":domain"))
}
