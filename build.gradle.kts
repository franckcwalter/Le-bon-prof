@file:Suppress("DSL_SCOPE_VIOLATION")

buildscript {
    dependencies {
        classpath(libs.androidx.safeargs)
    }
}


plugins {

    alias(libs.plugins.android.safeargs) apply false
}
