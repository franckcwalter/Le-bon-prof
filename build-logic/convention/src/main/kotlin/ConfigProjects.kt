import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {

        compileSdk = Config.android.compileSdkVersion


        defaultConfig {
            minSdk = Config.android.minSdkVersion
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = Config.jvm.javaVersion
            targetCompatibility = Config.jvm.javaVersion
        }

        kotlinOptions {
            jvmTarget = Config.jvm.kotlinJvm
        }



        dependencies {
            add("implementation", libs.findLibrary("androidx-ktx").get())
            add("implementation", libs.findLibrary("androidx-appcompat").get())
            add("implementation", libs.findLibrary("androidx-material").get())

            add("testImplementation", libs.findLibrary("junit").get())
            add("androidTestImplementation", libs.findLibrary("junitext").get())
            add("androidTestImplementation", libs.findLibrary("expresso").get())
        }
    }
}


internal fun Project.configureApp(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {

        dependencies {
            add("implementation", libs.findLibrary("koin").get())
            add("implementation", libs.findLibrary("koincompat").get())
        }

        buildFeatures {
            viewBinding = true
        }

        /*
        dataBinding {
            enable = true
        }*/

    }
}


internal fun Project.configureModuleDomain(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {

        dependencies {
            add("implementation", libs.findLibrary("moshi").get())
        }
    }
}

internal fun Project.configureModuleModel(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {

        dependencies {
            add("implementation", libs.findLibrary("koin").get())
            add("implementation", libs.findLibrary("koincompat").get())

            add("implementation", libs.findLibrary("moshi").get())
            add("implementation", libs.findLibrary("retrofit-moshiconverter").get())
            add("implementation", libs.findLibrary("retrofit-retrofit").get())
            add("implementation", libs.findLibrary("okhttp").get())
        }
    }
}


internal fun Project.configureModuleUi(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {

        dependencies {
            add("implementation", libs.findLibrary("koin").get())
            add("implementation", libs.findLibrary("koincompat").get())

            add("implementation", libs.findLibrary("moshi").get())
            add("implementation", libs.findLibrary("retrofit-moshiconverter").get())
            add("implementation", libs.findLibrary("retrofit-retrofit").get())
            add("implementation", libs.findLibrary("okhttp").get())

            add("implementation", libs.findLibrary("androidx-navigation-fragment").get())
            add("implementation", libs.findLibrary("androidx-navigation-ui").get())

            add("implementation", libs.findLibrary("androidx-fragment").get())
            add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel").get())

            add("implementation", libs.findLibrary("picasso").get())
            add("implementation", libs.findLibrary("androidx-material-icons-extended").get())
            add("implementation", libs.findLibrary("androidx-swiperefreshlayout").get())
        }

        buildFeatures {
            viewBinding = true
        }

        /*
        dataBinding {
            enable = true
        }
         */
    }
}






