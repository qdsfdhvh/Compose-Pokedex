package com.seiko.pokedex.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.seiko.base.router.Routes
import com.seiko.feature.detail.DetailScene
import com.seiko.feature.home.HomeScene

fun NavGraphBuilder.buildNavGraph() {
  composable(Routes.Home) { HomeScene() }
  composable(
    route = Routes.Detail.route,
    arguments = listOf(
      navArgument(Routes.Detail.argument01) { type = NavType.StringType }
    )
  ) {
    val pokemonName = it.arguments?.getString(Routes.Detail.argument01) ?: return@composable
    DetailScene(pokemonName)
  }
}