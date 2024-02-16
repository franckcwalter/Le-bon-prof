import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.plugin.extraProperties

class AndroidLibraryDomainConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")


                apply("kotlin-parcelize")
                apply("kotlin-kapt")
                apply("androidx.navigation.safeargs.kotlin")

            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = Config.android.targetSdkVersion
                configureModuleDomain(this)
            }
        }
    }
}
