plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.devid_academy.domain"
}

dependencies {

    core()

    tests()

    implementation(Dependencies.moshi)

}