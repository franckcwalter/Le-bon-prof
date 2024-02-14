plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}


group = "com.devid_academy.buildlogic"

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "conventionpluginsample.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "conventionpluginsample.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}


/*

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}


gradlePlugin{
    plugins{
        register("androidLibrary"){
            id="amsavarthan.android.library"
            implementationClass="AndroidLibraryConventionPlugin"
        }
    }
}*/





