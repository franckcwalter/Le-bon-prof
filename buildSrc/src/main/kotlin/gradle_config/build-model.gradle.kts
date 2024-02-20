package gradle_config

import retrofit
import core
import domain
import koin
import implementation
import tests
import Dependencies
import BuildPlugins
import MainGradlePlugin


plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.devid_academy.model"
}

dependencies {

    domain()

    core()

    tests()

    koin()
    retrofit()

}
