@Suppress("DSL_SCOPE_VIOLATION")
plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)

}

android {
    namespace = "com.devid_academy.projetfinal"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.devid_academy.projetfinal"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }

}

dependencies {

    implementation(project(":domain"))
    implementation(project(":model"))
    implementation(project(":ui"))

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)

    // tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.junitext)
    androidTestImplementation(libs.expresso)

    // KOIN
    implementation(libs.koin)
    implementation(libs.koincompat)

}