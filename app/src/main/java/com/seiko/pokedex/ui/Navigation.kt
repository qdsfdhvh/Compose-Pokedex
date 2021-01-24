package com.seiko.pokedex.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.seiko.base.router.Routes
import com.seiko.feature.home.HomeScene

fun NavGraphBuilder.buildNavGraph(navController: NavHostController) {
  composable(Routes.Home) { HomeScene() }
}