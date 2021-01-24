package com.seiko.common.compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.AmbientContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.seiko.base.router.initialRoute
import com.seiko.common.compose.AmbientNavController

@Composable
fun Router(
  navController: NavHostController = rememberNavController(),
  builder: NavGraphBuilder.() -> Unit
) {
  val context = AmbientContext.current
  Providers(
    AmbientNavController provides navController,
  ) {
    NavHost(
      navController = navController,
      startDestination = initialRoute,
      builder = builder
    )
  }
}