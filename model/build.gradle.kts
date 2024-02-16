plugins {
    id("conventionplugin.android.library.model")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.devid_academy.model"
}

dependencies {

    implementation(project(":domain"))

}

