package gradle_config

import retrofit
import core
import domain
import model
import ui
import koin
import implementation
import tests
import Dependencies
import BuildPlugins
import MainGradlePlugin
import BuildProjectConfig
import gradle.kotlin.dsl.accessors._1aac24b857164f2e0bb2ae9832f4e245.android



plugins {
    id("kotlin-android")
    id("com.android.application")
}



dependencies {

    domain()
    model()
    ui()

    core()
    tests()

    koin()

}