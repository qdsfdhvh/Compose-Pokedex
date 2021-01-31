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
  api(Compose.liveData)
  api(Compose.foundation)
  api(Compose.foundationLayout)
  api(Compose.animation)

  // ImageLoader https://github.com/chrisbanes/accompanist
  implementation("dev.chrisbanes.accompanist:accompanist-coil:0.5.0")

  kapt(Hilt.hiltCoreCompiler)
  kapt(Hilt.hiltCompiler)
}