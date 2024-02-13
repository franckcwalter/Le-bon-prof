plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN)
}

apply<MainGradlePlugin>()


android {
    namespace = "com.devid_academy.ui"
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