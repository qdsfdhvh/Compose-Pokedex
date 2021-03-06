const val kotlinAndroid: String = "android"
const val kotlinKapt: String = "kapt"

/**
 * @author seiko
 */
object Config {
  const val minSdkVersion = 23
  const val targetSdkVersion = 30
  const val compileSdkVersion = 30
  const val buildToolsVersions = "30.0.3"
}

/**
 * @author seiko
 */
object Versions {
  // Plugin
  const val androidGradleVersion = "7.0.0-alpha05"
  const val kotlinVersion = "1.4.21-2"

  // Androidx
  const val coreKtxVersion = "1.3.2"
  const val appCompatVersion = "1.2.0"
  const val materialVersion = "1.2.1"
  const val lifecycleVersion = "2.3.0-rc01"
  const val activityVersion = "1.2.0-rc01"
  const val fragmentVersion = "1.3.0-rc01"

  // ComposeUI
  const val composeVersion = "1.0.0-alpha11"
  const val composeNavVersion = "1.0.0-alpha05"
  const val composePagingVersion = "1.0.0-alpha06"

  // Hilt
  const val hiltDaggerVersions = "2.31-alpha"
  const val hiltVersion = "1.0.0-alpha03"

  // 协程
  const val coroutinesVersion = "1.4.1"

  // Json
  const val moshiVersion = "1.11.0"

  // Jetpack
  const val pagingVersion = "3.0.0-alpha12"

  // Test
  const val junitVersion = "4.13"
  const val testExtVersion = "1.1.2"
  const val espressoVersion = "3.3.0"
}

/**
 * Gradle
 * @author seiko
 */
object Plugin {
  const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradleVersion}"
  const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
  const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltDaggerVersions}"
}

/**
 * Android基础库
 * @author seiko
 */
object Androidx {
  const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
  const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
  const val material = "com.google.android.material:material:${Versions.materialVersion}"
  const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
  const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
  const val activity = "androidx.activity:activity-ktx:${Versions.activityVersion}"
  const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
}

/**
 * Dagger2&Hilt
 * @author seiko
 */
object Hilt {
  const val android = "com.google.dagger:hilt-android:${Versions.hiltDaggerVersions}"
  const val hiltCoreCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltDaggerVersions}"
  const val common = "androidx.hilt:hilt-common:${Versions.hiltVersion}"
  const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltVersion}"
  const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltVersion}"
  const val navigation = "androidx.hilt:hilt-navigation:${Versions.hiltVersion}"
}

/**
 * ComposeUI
 * @author seiko
 */
object Compose {
  const val ui = "androidx.compose.ui:ui:${Versions.composeVersion}"
  const val material = "androidx.compose.material:material:${Versions.composeVersion}"
  const val materialIconsExtends =
    "androidx.compose.material:material-icons-extended:${Versions.composeVersion}"
  const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
  const val runtime = "androidx.compose.runtime:runtime:${Versions.composeVersion}"
  const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavVersion}"
  const val liveData = "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"
  const val foundation = "androidx.compose.foundation:foundation:${Versions.composeVersion}"
  const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.composeVersion}"
  const val animation = "androidx.compose.animation:animation:${Versions.composeVersion}"
  const val paging = "androidx.paging:paging-compose:${Versions.composePagingVersion}"
}

/**
 * 协程
 * @author seiko
 */
object Coroutines {
  const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
}

/**
 * Json
 * @author seiko
 */
object Moshi {
  const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
  const val moshiCompiler = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
}

/**
 * Jetpack
 * @author seiko
 */
object Jetpack {
  const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"
}

/**
 * 其他
 * @author seiko
 */
object ThirdLib {
  const val timber = "com.jakewharton.timber:timber:4.7.1"
}

/**
 * Test
 * @author seiko
 */
object TestLib {
  const val junit = "junit:junit:${Versions.junitVersion}"
  const val testExt = "androidx.test.ext:junit:${Versions.testExtVersion}"
  const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
}

/**
 * 本地
 * @author seiko
 */
object ProjectLib {
  const val base = ":base"
  const val commonCompose = ":common:compose"
  const val data = ":data"
  const val featureHome = ":feature:home"
  const val featureDetail = ":feature:detail"
}