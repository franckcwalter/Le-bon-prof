import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                with(defaultConfig) {
                    targetSdk = Integer.parseInt(
                        libs.findVersion("projectTargetSdkVersion").get().toString()
                    )
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    versionCode = Integer.parseInt(
                        libs.findVersion("projectTargetSdkVersion").get().toString()
                    )
                    versionName = libs.findVersion("projectTargetSdkVersion").get().toString()
                }
            }
        }
    }
}


