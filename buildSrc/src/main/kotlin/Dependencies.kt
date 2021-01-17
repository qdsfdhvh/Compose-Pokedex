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
    // Androidx
    const val coreKtxVersion = "1.3.2"
    const val appCompatVersion = "1.2.0"
    const val materialVersion = "1.2.1"
    const val lifecycleVersion = "2.3.0-rc01"

    // ComposeUI
    const val composeVersion = "1.0.0-alpha08"

    // Test
    const val junitVersion = "4.13"
    const val testExtVersion = "1.1.2"
    const val espressoVersion = "3.3.0"
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
}

/**
 * ComposeUI
 * @author seiko
 */
object Compose {
    const val ui = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val material = "androidx.compose.material:material:${Versions.composeVersion}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
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