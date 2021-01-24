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
  implementation(project(ProjectLib.base))

  api(Compose.ui)
  api(Compose.material)
  api(Compose.materialIconsExtends)
  api(Compose.uiTooling)
  api(Compose.runtime)
  api(Compose.navigation)

  kapt(Hilt.hiltCoreCompiler)
  kapt(Hilt.hiltCompiler)
}