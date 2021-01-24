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
}

kapt {
  arguments {
    arg("rxhttp_package", "rxhttp")
  }
}

dependencies {
  implementation(project(ProjectLib.base))

  val rxHttpVersion = "2.5.4"
  implementation("com.ljx.rxhttp:rxhttp:${rxHttpVersion}")
  api("com.squareup.okhttp3:okhttp:4.9.0")
  kapt("com.ljx.rxhttp:rxhttp-compiler:${rxHttpVersion}")
  implementation("com.ljx.rxhttp:converter-moshi:${rxHttpVersion}")

  kapt(Hilt.hiltCoreCompiler)
  kapt(Hilt.hiltCompiler)
  kapt(Moshi.moshiCompiler)
}