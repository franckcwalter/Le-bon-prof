@Suppress("DSL_SCOPE_VIOLATION")
plugins {

    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN)

    id("conventionpluginsample.android.application")

}

android {
    namespace = "com.devid_academy.projetfinal"


    defaultConfig {
        applicationId = BuildProjectConfig.appId
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

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

}

dependencies {

    domain()
    model()
    ui()

    core()
    tests()

    koin()

}