plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")

    id("androidx.navigation.safeargs.kotlin")

    id("kotlin-parcelize")

}

android {
    namespace = "com.devid_academy.projetfinal"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.devid_academy.projetfinal"
        minSdk = 24
        targetSdk = 33
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

    // data et view binding
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // navigation
    val nav_version = "2.5.3" // 2.7.5
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //picasso
    implementation("com.squareup.picasso:picasso:2.8")

    // swipe refresh layout for RV
    // implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

}