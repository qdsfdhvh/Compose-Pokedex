plugins {
  androidLibrary
  kotlin(kotlinAndroid)
  kotlin(kotlinKapt)
}

android {
  compileSdkVersion(Config.compileSdkVersion)
  defaultConfig {
    minSdkVersion(Config.minSdkVersion)
    targetSdkVersion(Config.targetSdkVersion)
  }

  @Suppress("UnstableApiUsage")
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
}

dependencies {
  api(Androidx.coreKtx)
  api(Androidx.appCompat)
  api(Androidx.material)
  api(Androidx.activity)
  api(Androidx.fragment)

  api(Hilt.android)
  api(Hilt.common)
  api(Hilt.viewModel)
  kapt(Hilt.hiltCoreCompiler)
  kapt(Hilt.hiltCompiler)

  api(ThirdLib.timber)

  // initial https://github.com/NoEndToLF/AppStartFaster
  api("com.github.NoEndToLF:AppStartFaster:1.0.3")
}