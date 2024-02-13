@Suppress("DSL_SCOPE_VIOLATION")
plugins {

    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN)

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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