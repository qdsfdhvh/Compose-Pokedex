plugins {
    id("com.android.application")
    id("kotlin-android")
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
        kotlinCompilerVersion = "1.4.20"
    }
}

dependencies {
    implementation(Androidx.coreKtx)
    implementation(Androidx.appCompat)
    implementation(Androidx.material)
    implementation(Androidx.activity)
    implementation(Androidx.fragment)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiTooling)
    implementation(Androidx.liveData)
    implementation(Androidx.viewModel)
    testImplementation(TestLib.junit)
    androidTestImplementation(TestLib.testExt)
    androidTestImplementation(TestLib.espresso)
}