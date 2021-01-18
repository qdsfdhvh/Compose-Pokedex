plugins {
  androidLibrary
  kotlin(kotlinAndroid)
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
}