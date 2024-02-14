plugins {
    id("conventionplugin.android.library")
    id(BuildPlugins.KOTLIN_SAFE_ARGS)
}

android {
    namespace = "com.devid_academy.ui"

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    domain()

    core()
    tests()

    koin()
    retrofit()
    navigation()

    fragment()

    implementation(Dependencies.picasso)
    implementation(Dependencies.androidxSwiperefreshlayout)
    implementation(Dependencies.androidxMaterialIconsExtended)

}