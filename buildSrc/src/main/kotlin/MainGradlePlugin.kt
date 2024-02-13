import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class MainGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        applyPlugins(project)
        setProjectConfig(project)
    }

    private fun applyPlugins(project : Project){
        project.apply{
            plugin("com.android.library")
            plugin("kotlin-android")
            plugin("kotlin-parcelize")
            plugin("kotlin-kapt")
            plugin("androidx.navigation.safeargs.kotlin")
        }
    }

    private fun setProjectConfig(project: Project){
        project.android().apply {
            compileSdk = BuildProjectConfig.compileSdk

            defaultConfig {
                minSdk = BuildProjectConfig.minSdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            compileOptions{
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            buildFeatures {
                viewBinding = true
                dataBinding = true
            }
        }
    }

    private fun Project.android() : LibraryExtension {
        return extensions.getByType(LibraryExtension::class.java)
    }
}


