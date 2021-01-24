package com.seiko.common.compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.currentBackStackEntryAsState
import com.seiko.common.compose.AmbientApplication
import com.seiko.common.compose.AmbientNavController
import com.seiko.common.compose.AmbientViewModelProviderFactory
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import javax.inject.Inject

/**
 * Inspired from https://github.com/google/dagger/issues/2166#issuecomment-723775543
 *
 * PS: waiting for hilt-navigation release https://github.com/google/dagger/issues/2166#issuecomment-761061463
 */

val AmbientHiltDependencies = staticAmbientOf<HiltDependencies>()

@Composable
inline fun <reified VM : ViewModel> navViewModel(
  key: String? = null,
  factory: ViewModelProvider.Factory? = AmbientViewModelProviderFactory.current
): VM {
  val navController = AmbientNavController.current
  val backStackEntry = navController.currentBackStackEntryAsState().value
  return if (backStackEntry != null) {
    // Hack for navigation viewModel
    val application = AmbientApplication.current
    val hiltDependencies = AmbientHiltDependencies.current
    val delegatedFactory =
      SavedStateViewModelFactory(application, backStackEntry, backStackEntry.arguments)
    val hiltViewModelFactory = HiltViewModelFactory(
      backStackEntry,
      backStackEntry.arguments,
      hiltDependencies.viewModelInjectKeys,
      delegatedFactory,
      hiltDependencies.viewModelComponentBuilder,
    )
    viewModel(key, hiltViewModelFactory)
  } else {
    viewModel(key, factory)
  }
}

@Composable
fun ProvideHiltViewModelFactoryParams(
  hiltDependencies: HiltDependencies,
  content: @Composable () -> Unit,
) {

  Providers(AmbientHiltDependencies provides hiltDependencies) {
    content()
  }
}

class HiltDependencies @Inject constructor(
  @HiltViewModelMap.KeySet val viewModelInjectKeys: Set<String>,
  val viewModelComponentBuilder: ViewModelComponentBuilder,
)