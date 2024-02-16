import org.gradle.api.JavaVersion

data class AndroidConfig(
    val minSdkVersion : Int,
    val targetSdkVersion : Int,
    val compileSdkVersion : Int,
    val applicationId : String,
    val versionCode : Int,
    val versionName : String
)
data class JvmConfig(
    val javaVersion : JavaVersion,
    val kotlinJvm : String
)

object Config {
    val android = AndroidConfig(
        minSdkVersion = 24,
        targetSdkVersion = 33,
        compileSdkVersion = 34,
        applicationId = "com.devid_academy.projetfinal",
        versionCode = 1,
        versionName = "1.0",
    )

    val jvm = JvmConfig(
        javaVersion = JavaVersion.VERSION_17,
        kotlinJvm = "17"
    )
}

