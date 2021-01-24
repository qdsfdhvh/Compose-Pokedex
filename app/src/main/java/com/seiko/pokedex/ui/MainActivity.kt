package com.seiko.pokedex.ui

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.setContent
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.fragment.DialogFragmentNavigator
import com.seiko.common.compose.AmbientActivity
import com.seiko.common.compose.AmbientApplication
import com.seiko.common.compose.AmbientWindow
import com.seiko.common.compose.extensions.HiltDependencies
import com.seiko.common.compose.extensions.ProvideHiltViewModelFactoryParams
import com.seiko.common.compose.extensions.Router
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

  @Inject
  lateinit var hiltDependencies: HiltDependencies

  private val navController by lazy(LazyThreadSafetyMode.NONE) {
    NavHostController(this).apply {
      navigatorProvider.apply {
        addNavigator(ComposeNavigator())
        addNavigator(DialogFragmentNavigator(this@MainActivity, supportFragmentManager))
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // 软键盘弹出时不重绘
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      window.setDecorFitsSystemWindows(false)
    } else {
      @Suppress("DEPRECATION")
      window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    setContent {
      Providers(
        AmbientApplication provides application,
        AmbientActivity provides this,
        AmbientWindow provides window
      ) {
        // @HiltViewModel
        // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
        // @ViewModelInject
        // androidx.hilt.lifecycle.HiltViewModelFactory
        ProvideHiltViewModelFactoryParams(
          hiltDependencies = hiltDependencies
        ) {
          Router(
            navController = navController,
            builder = { buildNavGraph(navController) }
          )
        }
      }
    }
  }
}