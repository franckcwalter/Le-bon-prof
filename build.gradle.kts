@file:Suppress("DSL_SCOPE_VIOLATION")

buildscript {
    dependencies {
        classpath(libs.androidx.safeargs)
    }
}

plugins {

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.android.library) apply false

}