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
  implementation(project(ProjectLib.commonCompose))
  implementation(project(ProjectLib.data))

  kapt(Hilt.hiltCoreCompiler)
  kapt(Hilt.hiltCompiler)

  implementation("com.github.skydoves:progressview:1.1.0")

  implementation("androidx.palette:palette:1.0.0")
}