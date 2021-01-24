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

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
    useIR = true
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.composeVersion
    kotlinCompilerVersion = Versions.kotlinVersion
  }
}

dependencies {
  api(Compose.ui)
  api(Compose.material)
  api(Compose.materialIconsExtends)
  api(Compose.uiTooling)
  api(Compose.runtime)
  api(Compose.navigation)

  api(Hilt.android)
  api(Hilt.common)
  api(Hilt.viewModel)
  kapt(Hilt.hiltCoreCompiler)
  kapt(Hilt.hiltCompiler)
}