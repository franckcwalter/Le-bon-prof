
plugins {
    id("gradle_config.build-app")
    id("org.sonarqube") version "4.4.1.3373"
}

android {
    namespace = "com.devid_academy.projetfinal"
    compileSdk = BuildProjectConfig.compileSdk

    defaultConfig {
        applicationId = BuildProjectConfig.appId
        minSdk = BuildProjectConfig.minSdk
        targetSdk = BuildProjectConfig.targetSdk
        versionCode = BuildProjectConfig.versionCode
        versionName = BuildProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_17
        targetCompatibility = org.gradle.api.JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

}

sonar {
    properties {
        property("sonar.projectKey", "simplon-cda-test")
        property("sonar.projectName", "simplon-cda-test")
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}