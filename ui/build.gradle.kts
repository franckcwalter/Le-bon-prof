@Suppress("DSL_SCOPE_VIOLATION")
plugins {

    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)

    alias(libs.plugins.android.safeargs)

    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.kapt)

}

android {
    namespace = "com.devid_academy.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    // data et view binding
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}

dependencies {

    implementation(project(":domain"))

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

    // retrofit
    implementation(libs.okhttp)
    implementation(libs.moshi)
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.moshiconverter)

    // navigation
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.fragment)

    implementation(libs.picasso)

    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.androidx.material.icons.extended)

}