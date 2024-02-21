package gradle_config

import retrofit
import core
import implementation
import tests
import Dependencies
import MainGradlePlugin
import BuildPlugins

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.devid_academy.domain"
}

apply<MainGradlePlugin>()

dependencies {

    core()

    tests()

    retrofit()

}
