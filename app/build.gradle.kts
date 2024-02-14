@Suppress("DSL_SCOPE_VIOLATION")
plugins {

    id("conventionplugin.android.application")

}

android {
    namespace = "com.devid_academy.projetfinal"

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