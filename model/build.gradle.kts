plugins {
    id("conventionplugin.android.library")
}

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

