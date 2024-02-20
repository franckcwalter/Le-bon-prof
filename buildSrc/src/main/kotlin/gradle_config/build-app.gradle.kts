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