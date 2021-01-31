import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories.applyDefault()
  dependencies {
    classpath(Plugin.androidGradle)
    classpath(Plugin.kotlinGradle)
    classpath(Plugin.hiltGradle)
  }
}

allprojects {
  repositories.applyDefault()
  tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
}

subprojects {
  tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
      useIR = true
      freeCompilerArgs = listOf(
        "-Xallow-jvm-ir-dependencies",
        "-Xskip-prerelease-check",
        "-Xopt-in=kotlin.RequiresOptIn"
      )
    }
  }
}