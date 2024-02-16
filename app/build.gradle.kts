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
    }

}


dependencies {

    implementation(project(":domain"))
    implementation(project(":model"))
    implementation(project(":ui"))

}
