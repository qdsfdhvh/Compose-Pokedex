import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.androidApplication: PluginDependencySpec
  get() = id("com.android.application")

val PluginDependenciesSpec.androidLibrary: PluginDependencySpec
  get() = id("com.android.library")

val PluginDependenciesSpec.daggerHilt: PluginDependencySpec
  get() = id("dagger.hilt.android.plugin")

fun RepositoryHandler.applyDefault() {
  google()
  jcenter()
  mavenCentral()
  maven("https://jitpack.io")
  maven("https://dl.bintray.com/kotlin/kotlin-eap")
}