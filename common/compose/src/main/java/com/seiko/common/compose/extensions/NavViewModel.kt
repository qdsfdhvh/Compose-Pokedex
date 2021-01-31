package com.seiko.common.compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.viewinterop.viewModel
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.currentBackStackEntryAsState
import com.seiko.common.compose.AmbientNavController
import com.seiko.common.compose.AmbientViewModelProviderFactory
import dagger.MapKey
import javax.inject.Provider
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ComposeAssistedFactoryKey(
  val value: KClass<out ComposeAssistedFactory>,
)

interface ComposeAssistedFactory

val AmbientAssistedFactoryMap =
  staticAmbientOf<Map<Class<out ComposeAssistedFactory>, Provider<ComposeAssistedFactory>>>()

@Composable
inline fun <reified VM : ViewModel> navViewModel(
  key: String? = null,
  factory: ViewModelProvider.Factory? = AmbientViewModelProviderFactory.current,
): VM {
  val navController = AmbientNavController.current
  val backStackEntry = navController.currentBackStackEntryAsState().value
  return if (backStackEntry != null) {
    viewModel(key, HiltViewModelFactory(
      context = AmbientContext.current,
      navBackStackEntry = backStackEntry
    ))
  } else {
    viewModel(key, factory)
  }
}

@Composable
inline fun <reified AF : ComposeAssistedFactory, reified VM : ViewModel> assistedViewModel(
  key: String? = null,
  noinline creator: (AF) -> VM,
): VM {
  val assistedFactoryMap = AmbientAssistedFactoryMap.current
  val factory = remember {
    val assistedFactory = assistedFactoryMap[AF::class.java]
      ?: error("could not find ComposeAssistedFactory with ${AF::class.java}")
    object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return creator(assistedFactory.get() as AF) as T
      }
    }
  }
  return viewModel(key = key, factory = factory)
}
