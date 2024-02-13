import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val androidxAppcompat = "androidx.appcompat:appcompat:${BuildDependenciesVersions.appcompat}"
    const val androidxFragment = "androidx.fragment:fragment-ktx:${BuildDependenciesVersions.fragment}"
    const val androidxKtx = "androidx.core:core-ktx:${BuildDependenciesVersions.ktx}"
    const val androidxLifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.lifecycleViewmodel}"
    const val androidxMaterial = "com.google.android.material:material:${BuildDependenciesVersions.material}"
    const val androidxMaterialIconsExtended = "androidx.compose.material:material-icons-extended:${BuildDependenciesVersions.materialIconsExtended}"
    const val androidxNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.navigation}"
    const val androidxNavigationUi = "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.navigation}"
    const val androidxSafeargs = "androidx.navigation:navigation-safe-args-gradle-plugin:${BuildDependenciesVersions.navigation}"
    const val androidxSwiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${BuildDependenciesVersions.swiperefreshlayout}"
    const val expresso = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.expresso}"
    const val junit = "junit:junit:${BuildDependenciesVersions.junit}"
    const val junitext = "androidx.test.ext:junit:${BuildDependenciesVersions.junitext}"
    const val koin = "io.insert-koin:koin-android:${BuildDependenciesVersions.koin}"
    const val koincompat = "io.insert-koin:koin-android-compat:${BuildDependenciesVersions.koin}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${BuildDependenciesVersions.moshi}"
    const val okhttp = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.okhttp}"
    const val picasso = "com.squareup.picasso:picasso:${BuildDependenciesVersions.picasso}"
    const val retrofitMoshiconverter = "com.squareup.retrofit2:converter-moshi:${BuildDependenciesVersions.retrofit}"
    const val retrofitRetrofit = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.retrofit}"
}

fun DependencyHandler.core() {
    implementation(Dependencies.androidxKtx)
    implementation(Dependencies.androidxAppcompat)
    implementation(Dependencies.androidxMaterial)
}

fun DependencyHandler.retrofit(){
    implementation(Dependencies.retrofitRetrofit)
    implementation(Dependencies.retrofitMoshiconverter)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.moshi)
}

fun DependencyHandler.koin(){
    implementation(Dependencies.koin)
    implementation(Dependencies.koincompat)
}

fun DependencyHandler.tests(){
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitext)
    androidTestImplementation(Dependencies.expresso)
}

fun DependencyHandler.navigation(){
    implementation(Dependencies.androidxNavigationUi)
    implementation(Dependencies.androidxNavigationFragment)
}

fun DependencyHandler.fragment(){
    implementation(Dependencies.androidxLifecycleViewmodel)
    implementation(Dependencies.androidxFragment)
}

fun DependencyHandler.model(){
    implementation(project(BuildModules.MODEL))
}
fun DependencyHandler.domain(){
    implementation(project(BuildModules.DOMAIN))
}
fun DependencyHandler.ui(){
    implementation(project(BuildModules.UI))
}