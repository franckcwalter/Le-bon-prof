plugins {

    id("conventionplugin.android.library")

    id(BuildPlugins.KOTLIN_PARCELIZE)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.KOTLIN_SAFE_ARGS)

}

android {

namespace = "com.devid_academy.domain"

}

dependencies {

    core()
    tests()

    implementation(Dependencies.moshi)

}