plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.devid_academy.model"
}

dependencies {

    domain()

    core()

    tests()

    koin()
    retrofit()

}
