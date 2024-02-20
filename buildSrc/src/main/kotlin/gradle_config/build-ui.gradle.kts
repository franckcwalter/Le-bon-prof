package gradle_config

import Dependencies
import MainGradlePlugin
import core
import domain
import fragment
import implementation
import koin
import navigation
import retrofit
import tests

plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.devid_academy.ui"
}

dependencies {

    domain()

    core()
    tests()

    koin()
    retrofit()
    navigation()

    fragment()

    implementation(Dependencies.picasso)
    implementation(Dependencies.androidxSwiperefreshlayout)
    implementation(Dependencies.androidxMaterialIconsExtended)

}