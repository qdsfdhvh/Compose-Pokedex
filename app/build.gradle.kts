plugins {
  androidApplication
  kotlin(kotlinAndroid)
  kotlin(kotlinKapt)
  daggerHilt
}

android {
  compileSdkVersion(Config.compileSdkVersion)
  buildToolsVersion(Config.buildToolsVersions)

  defaultConfig {
    applicationId = "com.seiko.pokedex"
    minSdkVersion(Config.minSdkVersion)
    targetSdkVersion(Config.targetSdkVersion)
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
          getDefaultProguardFile("proguard-android-optimize.txt"),
          "proguard-rules.pro"
      )
    }
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
  // Hilt
  implementation(Hilt.android)
  kapt(Hilt.hiltCoreCompiler)
  kapt(Hilt.hiltCompiler)

  implementation(project(ProjectLib.base))
  implementation(project(ProjectLib.commonCompose))
  implementation(project(ProjectLib.data))
  implementation(project(ProjectLib.featureHome))

  implementation("androidx.navigation:navigation-fragment-ktx:2.3.2")

  testImplementation(TestLib.junit)
  androidTestImplementation(TestLib.testExt)
  androidTestImplementation(TestLib.espresso)
}