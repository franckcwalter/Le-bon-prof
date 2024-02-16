plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    compileOnly("com.android.tools.build:gradle:8.1.3")
}

group = "com.devid_academy.buildlogic"

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "conventionplugin.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibraryDomain") {
            id = "conventionplugin.android.library.domain"
            implementationClass = "AndroidLibraryDomainConventionPlugin"
        }


        register("androidLibraryModel") {
            id = "conventionplugin.android.library.model"
            implementationClass = "AndroidLibraryModelConventionPlugin"
        }


        register("androidLibraryUi") {
            id = "conventionplugin.android.library.ui"
            implementationClass = "AndroidLibraryUiConventionPlugin"
        }

    }
}




