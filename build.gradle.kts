@file:Suppress("DSL_SCOPE_VIOLATION")

buildscript {
    dependencies {
        classpath(libs.androidx.safeargs)
    }
}


plugins {
    alias(libs.plugins.android.safeargs) apply false
    id("org.sonarqube") version "4.4.1.3373"

}

sonarqube {
    properties {
        property("sonar.projectKey", "simplon-cda-test")
        property("sonar.projectName", "simplon-cda-test")
    }
}

